package br.com.codenation.mapfood.repository;

import br.com.codenation.mapfood.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductsRepository extends MongoRepository<Product, String>, CrudRepository<Product, String> {
    List<Product> findByRestaurantId (String restaurantId);
}
