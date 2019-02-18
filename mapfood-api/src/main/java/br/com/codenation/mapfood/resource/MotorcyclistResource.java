package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Motorcyclist;
import br.com.codenation.mapfood.service.MotorcyclistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Motorcyclist", description = "Manipulating Motorcyclist")
@RestController
@RequestMapping("/motorcyclist")
public class MotorcyclistResource {

    @Autowired
    private MotorcyclistService service;

    @ApiOperation(value = "Find motorcyclist")
    @GetMapping
    public List<Motorcyclist> getAllAvailable(){
        return service.findByAvailable();
    }
}
