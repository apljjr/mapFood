package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Motorcyclist;
import br.com.codenation.mapfood.document.Order;
import br.com.codenation.mapfood.document.Restaurant;
import br.com.codenation.mapfood.service.MotorcyclistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Motorcyclist", description = "Manipulating Motorcyclist")
@RestController
@RequestMapping(value = "/motorcyclists")
public class MotorcyclistResource {

    private final MotorcyclistService motorcyclistService;

    @Autowired
    public MotorcyclistResource (MotorcyclistService motorcyclistService) {
        this.motorcyclistService = motorcyclistService;
    }

    @ApiOperation(value = "Find all registered motorcyclists")
    @GetMapping(value = "")
    public List<Motorcyclist> getAll() {

        return motorcyclistService.findAll();
    }

    @ApiOperation(value = "Find all available motorcyclists near a point")
    @GetMapping(value = "/available-{distance}")
    public Motorcyclist getTheNearestMotorcyclist(@RequestBody Restaurant restaurant, @PathVariable double distance) {

        return motorcyclistService.findTheNearestMotorcyclist(restaurant.getLocation(),distance);
    }

    @ApiOperation(value = "Turn on Find the a motorcyclist by id")
    @PutMapping(value = "/{id}")
    public Motorcyclist getById(@PathVariable("id") String id) {
        return motorcyclistService.findById(id);
    }
}
