package br.com.codenation.mapfood.repository;

import br.com.codenation.mapfood.document.User;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsersRepository extends MongoRepository<User, String> {
    List<User> findByLocationNear(Point point, Distance distance);
}
