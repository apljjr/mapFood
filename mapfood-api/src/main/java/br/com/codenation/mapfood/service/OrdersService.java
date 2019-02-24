package br.com.codenation.mapfood.service;

import br.com.codenation.mapfood.document.Item;
import br.com.codenation.mapfood.document.Order;
import br.com.codenation.mapfood.document.Restaurant;
import br.com.codenation.mapfood.exception.InvalidOrderException;
import br.com.codenation.mapfood.exception.OrderNotFoundException;
import br.com.codenation.mapfood.exception.RestaurantNotFoundException;
import br.com.codenation.mapfood.repository.OrdersRepository;
import br.com.codenation.mapfood.repository.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.codenation.mapfood.document.OrderStatus.PENDING;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final RestaurantsRepository restaurantsRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, RestaurantsRepository restaurantsRepository) {
        this.ordersRepository = ordersRepository;
        this.restaurantsRepository = restaurantsRepository;
    }

    public Order save(Order order) throws InvalidOrderException {
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
            order.setStatus(PENDING);
            order.setTimestamp(LocalDateTime.now());
            return ordersRepository.save(order);
        }

        throw new InvalidOrderException("Só é permitido pedidos de um resturante por vez!");
    }

    public List<Order> getAll() {
        return ordersRepository.findAll();
}

    public Order getOne(String id) {
         return ordersRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }
}
