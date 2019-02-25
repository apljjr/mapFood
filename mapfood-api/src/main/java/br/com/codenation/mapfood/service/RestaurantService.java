package br.com.codenation.mapfood.service;

import br.com.codenation.mapfood.document.Order;
import br.com.codenation.mapfood.document.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    List<Restaurant> findByLocationNear(Point location, Distance distance);
    Optional<Restaurant> findById (String id);
    Order getOldestReadyOrderByRestaurant(Order order);
}
