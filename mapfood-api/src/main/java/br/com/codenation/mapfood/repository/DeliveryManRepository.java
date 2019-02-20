package br.com.codenation.mapfood.repository;

import br.com.codenation.mapfood.document.DeliveryMan;
import br.com.codenation.mapfood.document.Restaurant;
import org.bson.types.ObjectId;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DeliveryManRepository extends MongoRepository<DeliveryMan, String> {
    List<DeliveryMan> findByLocationNear(Point point, Distance distance);
}
