package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Resource<T> {

    ResponseEntity<List<T>> findAll();

    ResponseEntity<?> findById(String id);


}
