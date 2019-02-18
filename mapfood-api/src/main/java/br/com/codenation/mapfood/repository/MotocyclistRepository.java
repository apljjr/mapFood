package br.com.codenation.mapfood.repository;

import br.com.codenation.mapfood.document.Motorcyclist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotocyclistRepository extends MongoRepository<Motorcyclist, String>, CrudRepository<Motorcyclist, String>{

}
