package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Motorcyclist;
import br.com.codenation.mapfood.service.MotorcyclistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Motorcyclist", description = "Manipulating Motorcyclist")
@RestController
@RequestMapping(value = "/motorcyclists")
public class MotorcyclistResource {

    @Autowired
    private MotorcyclistService service;

    @ApiOperation(value = "Find all registered motorcyclists")
    @GetMapping(value = "")
    public List<Motorcyclist> getAll() {

        return service.findAll();
    }

    @ApiOperation(value = "Find all available motorcyclists near a point")
    @GetMapping(value = "/available-{distance}")
    public List<Motorcyclist> getAllAvailable(@PathVariable double distance) {

        GeoJsonPoint point = new GeoJsonPoint(-51.2287,-30.0277 );

        return service.findAllNearByAvailable(point,distance);
    }

    @ApiOperation(value = "Find the a motorcyclist by id")
    @PutMapping(value = "/{id}")
    public Motorcyclist getById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @ApiOperation(value = "Update the available value of a motorcyclist")
    @PutMapping(value = "/{id}")
    public void updateById(@PathVariable("id") String id, @Valid @RequestBody Motorcyclist motorcyclist) {
        motorcyclist.set_id(id);
        service.save(motorcyclist);
    }
}
