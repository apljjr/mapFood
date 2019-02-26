package br.com.codenation.mapfood.service.impl;

import br.com.codenation.mapfood.document.Order;
import br.com.codenation.mapfood.document.OrderStatus;
import br.com.codenation.mapfood.document.Restaurant;
import br.com.codenation.mapfood.document.User;
import br.com.codenation.mapfood.exception.OrderNotFoundException;
import br.com.codenation.mapfood.exception.RestaurantNotFoundException;
import br.com.codenation.mapfood.repository.OrdersRepository;
import br.com.codenation.mapfood.repository.RestaurantsRepository;
import br.com.codenation.mapfood.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantsRepository restaurantsRepository;
    private final OrdersRepository ordersRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantsRepository restaurantsRepository, OrdersRepository ordersRepository) {
        this.restaurantsRepository = restaurantsRepository;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<Restaurant> findByLocationNear(double latitude, double longitude,
                                               double radius) {
        Point location = new Point(longitude, latitude);
        Distance distance = new Distance(radius, Metrics.KILOMETERS);

        return restaurantsRepository.findByLocationNear(location, distance);
    }

    @Override
    public Restaurant findById(String id) {
        return restaurantsRepository.findById(id).orElseThrow(RestaurantNotFoundException::new);
    }


    @Override
    public Order getOldestReadyOrderByRestaurant(Order order) {
        List<Order> allOrders = ordersRepository.findByRestaurantId(order.getRestaurant().getId());

        return allOrders.stream()
            .filter(o -> o.getStatus().equals(OrderStatus.READY))
            .sorted(Comparator.comparing(Order::getTimestamp).reversed())
            .findFirst()
            .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public List<Order> getAllOrdersNearOldestReadyOrder(User oldestOrderUser, String restaurantId, double distance){

        List<Order> allOrders = ordersRepository.findByRestaurantIdAndUserLocationNear(
            restaurantId,
            oldestOrderUser.getLocation(),
            new Distance(distance, Metrics.KILOMETERS));

        return allOrders.stream()
            .filter(o -> o.getStatus().equals(OrderStatus.READY))
            .limit(5)
            .collect(Collectors.toList());
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantsRepository.findAll();
    }
}
