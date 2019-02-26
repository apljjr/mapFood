package br.com.codenation.mapfood.repository;

import br.com.codenation.mapfood.document.Order;
import br.com.codenation.mapfood.document.Restaurant;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrdersRepository extends MongoRepository<Order, String> {

    List<Order> findByUserId(String userId);

    List<Order> findByRestaurant(Restaurant restaurant);

    List<Order> findByRestaurantId(String id);

    List<Order> findByRestaurantIdAndUserLocationNear(String restaurantId, Point point, Distance distance);

    List<Order> findByMotorcyclistId(String motorcyclistId);

}
