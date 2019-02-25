package br.com.codenation.mapfood.service.impl;

import br.com.codenation.mapfood.document.Restaurant;
import br.com.codenation.mapfood.exception.RestaurantNotFoundException;
import br.com.codenation.mapfood.repository.RestaurantsRepository;
import br.com.codenation.mapfood.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantsRepository restaurantsRepository;

    @Autowired
    public RestaurantServiceImpl (RestaurantsRepository restaurantsRepository) {
        this.restaurantsRepository = restaurantsRepository;
    }

    @Override
    public List<Restaurant> findByLocationNear(Point location, Distance distance) {
        return restaurantsRepository.findByLocationNear(location, distance);
    }

    @Override
    public Restaurant findById(String id) {
        return restaurantsRepository.findById(id).orElseThrow(RestaurantNotFoundException::new);
    }
}
