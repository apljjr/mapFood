package br.com.codenation.mapfood.service;

import br.com.codenation.mapfood.document.Order;
import br.com.codenation.mapfood.document.Restaurant;
import br.com.codenation.mapfood.document.User;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> findByLocationNear(Point location, Distance distance);

    Restaurant findById (String id);

    Order getOldestReadyOrderByRestaurant(Order order);

    List<Order> getAllOrdersNearOldestReadyOrder(User oldestOrderUser, String restaurantId, double distance);
}
