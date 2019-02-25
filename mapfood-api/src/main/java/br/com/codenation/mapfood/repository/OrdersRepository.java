package br.com.codenation.mapfood.repository;

import br.com.codenation.mapfood.document.Order;
import br.com.codenation.mapfood.document.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrdersRepository extends MongoRepository<Order, String> {

    List<Order> findByRestaurant(Restaurant restaurant);

    @Query("{Restaurant._id : ?0}")
    public List<Order> findByRestaurantId(String id);

}
