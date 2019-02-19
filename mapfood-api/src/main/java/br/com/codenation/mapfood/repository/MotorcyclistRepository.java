package br.com.codenation.mapfood.repository;

import br.com.codenation.mapfood.document.Motorcyclist;
import org.bson.types.ObjectId;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MotorcyclistRepository extends MongoRepository<Motorcyclist, ObjectId>{

    List<Motorcyclist> findByLocationWithin(Circle c);

}
