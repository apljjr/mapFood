package br.com.codenation.mapfood.repository;

import br.com.codenation.mapfood.document.Restaurant;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RestaurantsRepository extends MongoRepository<Restaurant, String> {
    List<Restaurant> findByLocationWithin(Circle c);
}