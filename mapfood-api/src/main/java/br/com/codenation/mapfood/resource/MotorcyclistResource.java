package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Motorcyclist;
import br.com.codenation.mapfood.repository.MotorcyclistRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Motorcyclist", description = "Manipulating Motorcyclist")
@RestController
@RequestMapping(value = "/motorcyclists")
public class MotorcyclistResource {

    @Autowired
    private MotorcyclistRepository repository;

    @ApiOperation(value = "Find motorcyclist")
    @GetMapping(value = "/")
    public List<Motorcyclist> getAllAvailable(){

        return repository.findAll();
    }
}
