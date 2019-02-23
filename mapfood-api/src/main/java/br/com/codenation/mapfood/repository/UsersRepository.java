package br.com.codenation.mapfood.repository;

import br.com.codenation.mapfood.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, String> {
}
