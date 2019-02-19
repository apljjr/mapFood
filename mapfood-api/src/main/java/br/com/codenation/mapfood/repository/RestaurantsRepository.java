package br.com.codenation.mapfood.repository;

import br.com.codenation.mapfood.document.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantsRepository extends MongoRepository<Restaurant, String> {
}
