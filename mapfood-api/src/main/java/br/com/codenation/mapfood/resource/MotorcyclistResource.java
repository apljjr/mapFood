package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Motorcyclist;
import br.com.codenation.mapfood.document.Restaurant;
import br.com.codenation.mapfood.service.MotorcyclistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Motorcyclist", description = "Manipulating Motorcyclist")
@RestController
@RequestMapping(value = "/motorcyclists")
public class MotorcyclistResource implements Resource<Motorcyclist>{

    private final MotorcyclistService motorcyclistService;

    @Autowired
    public MotorcyclistResource (MotorcyclistService motorcyclistService) {
        this.motorcyclistService = motorcyclistService;
    }

    @Override
    @ApiOperation(value = "Find all registered motorcyclists")
    @GetMapping(value = "")
    public ResponseEntity<List<Motorcyclist>> findAll() {

        return new ResponseEntity(motorcyclistService.findAll(),HttpStatus.OK);
    }

    @Override
    @ApiOperation(value = "Turn on Find the a motorcyclist by id")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        return new ResponseEntity(motorcyclistService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Find all available motorcyclists near a point")
    @GetMapping(value = "/available-{distance}")
    public Motorcyclist getTheNearestMotorcyclist(@RequestBody Restaurant restaurant, @PathVariable double distance) {

        return motorcyclistService.findTheNearestMotorcyclist(restaurant.getLocation(),distance);
    }


}
