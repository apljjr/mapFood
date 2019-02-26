package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Restaurant;
import br.com.codenation.mapfood.service.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Restaurant", description = "Manipulating Restaurant")
@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantsResource implements Resource<Restaurant> {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantsResource(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @ApiOperation(value = "Find restaurants within radius")
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findRestaurantsWithinRadius(
            @RequestParam double latitude, @RequestParam double longitude,
            @RequestParam double radius) {

        return new ResponseEntity<List>(restaurantService.findByLocationNear(latitude, longitude, radius)
                                        ,HttpStatus.OK)
        ;
    }

    @Override
    @ApiOperation(value = "Find all registered motorcyclists")
    @GetMapping(value = "")
    public ResponseEntity<List<Restaurant>> findAll() {
        return new ResponseEntity<>(restaurantService.findAll(), HttpStatus.OK);
    }

    @Override
    @ApiOperation(value = "Find restaurants by id")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return new ResponseEntity<>(restaurantService.findById(id), HttpStatus.OK);
    }
}
