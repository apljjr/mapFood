package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Restaurant;
import br.com.codenation.mapfood.service.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api (value = "Restaurant", description = "Manipulating Restaurant")
@RestController
@RequestMapping (value = "/restaurants")
public class RestaurantsResource implements Resource<Restaurant>{

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantsResource (RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @ApiOperation(value = "Find restaurants within radius")
    @GetMapping (produces = "application/json")
    public ResponseEntity<?> findRestaurantsWithinRadius(
        @RequestParam double latitude, @RequestParam double longitude,
        @RequestParam double radius) {

        Point userLocation = new Point(longitude, latitude);
        Distance distance = new Distance(radius, Metrics.KILOMETERS);
        List<Restaurant> restaurantList = restaurantService.findByLocationNear(userLocation, distance);

        return new ResponseEntity<List>(restaurantList, HttpStatus.OK);
    }

    @Override
    @ApiOperation(value = "Find all registered motorcyclists")
    @GetMapping(value = "")
    public ResponseEntity<List<Restaurant>> findAll() {
        return new ResponseEntity<>(restaurantService.findAll(),HttpStatus.OK);
    }

    @Override
    @ApiOperation(value = "Find restaurants by id")
    @GetMapping (value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return new ResponseEntity<>(restaurantService.findById(id), HttpStatus.OK);
    }
}
