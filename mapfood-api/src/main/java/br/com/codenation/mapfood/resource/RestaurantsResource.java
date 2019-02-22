package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Restaurant;
import br.com.codenation.mapfood.repository.RestaurantsRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Api (value = "Restaurant", description = "Manipulating Restaurant")
@RestController
@RequestMapping (value = "/restaurants")
public class RestaurantsResource {

    @Autowired
    private RestaurantsRepository restaurantsRepository;

    @ApiOperation(value = "Find restaurants within radius")
    @GetMapping (produces = "application/json")
    public ResponseEntity<?> findRestaurantsWithinRadius(
        @RequestParam double latitude, @RequestParam double longitude,
        @RequestParam double radius) {

        Point userLocation = new Point(longitude, latitude);
        Distance distance = new Distance(radius, Metrics.KILOMETERS);
        List<Restaurant> restaurantList = restaurantsRepository.findByLocationNear(userLocation, distance);

        return new ResponseEntity<List>(restaurantList, HttpStatus.OK);
    }

    @ApiOperation(value = "Find restaurants by id")
    @GetMapping (value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findRestaurantById(@PathVariable String id) {
        Optional<Restaurant> restaurantOptional =
            restaurantsRepository.findById(id);

        return new ResponseEntity<>(restaurantOptional.get(), HttpStatus.OK);
    }
}
