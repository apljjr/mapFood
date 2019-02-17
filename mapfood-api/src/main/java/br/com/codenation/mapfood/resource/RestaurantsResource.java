package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Restaurant;
import br.com.codenation.mapfood.repository.RestaurantsRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api (value = "Restaurant", description = "Manipulating Restaurant")
@RestController
@RequestMapping (value = "/restaurants")
public class RestaurantsResource {

    @Autowired
    private RestaurantsRepository restaurantsRepository;

    @ApiOperation(value = "Find restaurants")
    @GetMapping (produces = "application/json")
    public ResponseEntity<?> findRestaurants() {

        List<Restaurant> restaurantList;

        restaurantList = restaurantsRepository.findAll();

        return new ResponseEntity<List>(restaurantList, HttpStatus.OK);
    }
}
