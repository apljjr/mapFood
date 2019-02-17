package br.com.codenation.mapfood.controller;

import br.com.codenation.mapfood.model.Motorcyclist;
import br.com.codenation.mapfood.repository.MotocyclistRepository;
import br.com.codenation.mapfood.service.MotorcyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/motorcyclists")
public class MotorcyclistController {

    @Autowired
    private MotorcyclistService service;

    @GetMapping
    public List<Motorcyclist> getAllAvailable(){
        return service.findByAvailable();
    }
}
