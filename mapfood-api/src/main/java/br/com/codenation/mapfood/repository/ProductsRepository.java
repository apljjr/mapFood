package br.com.codenation.mapfood.repository;

import br.com.codenation.mapfood.document.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductsRepository extends MongoRepository<Products, String> {
    List<Products> findByRestaurantId (String restaurantId);
}
