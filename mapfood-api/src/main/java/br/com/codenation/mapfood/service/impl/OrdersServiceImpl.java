package br.com.codenation.mapfood.service.impl;

import br.com.codenation.mapfood.document.*;
import br.com.codenation.mapfood.exception.InvalidOrderException;
import br.com.codenation.mapfood.exception.OrderNotFoundException;
import br.com.codenation.mapfood.exception.RestaurantNotFoundException;
import br.com.codenation.mapfood.repository.OrdersRepository;
import br.com.codenation.mapfood.repository.RestaurantsRepository;
import br.com.codenation.mapfood.repository.UsersRepository;
import br.com.codenation.mapfood.service.MotorcyclistService;
import br.com.codenation.mapfood.service.OrdersService;
import br.com.codenation.mapfood.service.RoutesService;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.codenation.mapfood.document.OrderStatus.ACCEPTED;
import static br.com.codenation.mapfood.document.OrderStatus.DELIVERED;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final RestaurantsRepository restaurantsRepository;
    private final MotorcyclistService motorcyclistService;


    private static final Double RADIUS = 50.0;
    private final RoutesService routesService;
    private final UsersRepository usersRepository;

    @Autowired
    public OrdersServiceImpl(OrdersRepository ordersRepository,
                             RestaurantsRepository restaurantsRepository,
                             MotorcyclistService motorcyclistService,
                             RoutesService routesService,
                             UsersRepository usersRepository) {
        this.ordersRepository = ordersRepository;
        this.restaurantsRepository = restaurantsRepository;
        this.motorcyclistService = motorcyclistService;
        this.routesService = routesService;
        this.usersRepository = usersRepository;
    }

    @Override
    public Order save(Order order) {
        String restaurantId = order.getRestaurant().getId();

        Optional<Restaurant> restaurant = restaurantsRepository.findById(restaurantId);

        boolean isValidOrder;

        if (restaurant.isPresent()) {
            List<String> incomingItemIds = order.getItems()
                    .stream()
                    .map(item -> item.getItem().getId())
                    .collect(Collectors.toList());

            List<String> restaurantItemIds = restaurant.get().getItems()
                    .stream()
                    .map(Item::getId)
                    .collect(Collectors.toList());

            isValidOrder = restaurantItemIds.containsAll(incomingItemIds);
        } else {
            throw new RestaurantNotFoundException();
        }

        if (isValidOrder) {
            order.setTimestamp(LocalDateTime.now());
            Order savedOrder = ordersRepository.save(order);
            Optional<Order> newOrder = ordersRepository.findById(savedOrder.getId());
            return ordersRepository.save(fillOrderDetails(newOrder.get()));
        }

        throw new InvalidOrderException("Só é permitido receber pedidos de um restaurante por vez!");
    }

    @Override
    public List<Order> getAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Order getOne(String id) {
        return ordersRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public List<Order> processOrdersToDeliver(Motorcyclist motorcyclist) {
        List<Order> orders = ordersRepository.findByMotorcyclistId(motorcyclist.getId());
        DirectionsResult directionsResult = routesService.getRoute(orders.get(0).getRestaurant().getLocation(), orders.stream().map(order -> order.getUser().getLocation()).collect(Collectors.toList()));
        DirectionsRoute bestRoute = directionsResult.routes[0];
        long totalDuration = 0L;
        long totalDistance = 0L;

        for (DirectionsLeg leg : bestRoute.legs) {
            totalDuration += leg.duration.inSeconds;
            totalDistance += leg.distance.inMeters;
        }

        for (Order order : orders) {
            order.setStatus(DELIVERED);
            order.setRoute(directionsResult.routes[0]);
            order.setTotalDistanceInMeters(totalDistance);
            order.setDeliveryTime(Duration.ofMinutes(totalDuration));
            ordersRepository.save(order);
        }

        return orders;
    }

    private Motorcyclist findAvailableMotorcyclist(Restaurant restaurant, User user) {
        Optional<Order> orderOptional = usersRepository.findByLocationNear(user.getLocation(), new Distance(15, Metrics.KILOMETERS))
                .stream()
                .map(u -> ordersRepository.findByUserId(u.getId()))
                .flatMap(u -> u.stream())
                .filter(order -> order.getRestaurant().getId().equals(restaurant.getId()))
                .filter(order -> order.getTimestamp().isAfter(LocalDateTime.now().plusMinutes(10)))
                .findFirst();
        if (orderOptional.isPresent()) {
            return orderOptional.get().getMotorcyclist();
        }
        return motorcyclistService.findTheNearestMotorcyclist(restaurant.getLocation(), RADIUS);
    }

    private Order fillOrderDetails(Order order) {
        Motorcyclist motorcyclist = findAvailableMotorcyclist(order.getRestaurant(), order.getUser());

        order.setMotorcyclist(motorcyclist);
        order.setStatus(ACCEPTED);
        return order;
    }
}
