package br.com.codenation.mapfood.service.impl;

import br.com.codenation.mapfood.document.Motorcyclist;
import br.com.codenation.mapfood.repository.MotocyclistRepository;
import br.com.codenation.mapfood.service.MotorcyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorcyclistServiceImpl implements MotorcyclistService {

   // @Autowired
    private MotocyclistRepository repository;

    public List<Motorcyclist> findByAvailable(){
        return null;
    }
}
