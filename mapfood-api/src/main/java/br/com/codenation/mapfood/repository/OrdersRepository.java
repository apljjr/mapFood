package br.com.codenation.mapfood.repository;

import br.com.codenation.mapfood.document.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersRepository extends MongoRepository<Order, String> {
}
