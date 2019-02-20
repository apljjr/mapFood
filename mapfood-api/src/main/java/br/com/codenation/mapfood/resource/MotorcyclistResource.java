package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Motorcyclist;
import br.com.codenation.mapfood.service.MotorcyclistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Motorcyclist", description = "Manipulating Motorcyclist")
@RestController
@RequestMapping(value = "/motorcyclists")
public class MotorcyclistResource {

    @Autowired
    private MotorcyclistService service;

    @ApiOperation(value = "Find all registered motorcyclists")
    @GetMapping(value = "/")
    public List<Motorcyclist> getAll() {

        return service.findAll();
    }

    @ApiOperation(value = "Find all available motorcyclists near a point")
    @GetMapping(value = "/available{radius}")
    public List<Motorcyclist> getAllAvailable(@PathVariable double radius) {

        GeoJsonPoint point = new GeoJsonPoint(-30.0277, -51.2287);

        return service.findByAvailable(point,radius);
    }
}
