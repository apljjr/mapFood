package br.com.codenation.mapfood.service.impl;

import br.com.codenation.mapfood.document.Motorcyclist;
import br.com.codenation.mapfood.repository.MotorcyclistRepository;
import br.com.codenation.mapfood.service.MotorcyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotorcyclistServiceImpl implements MotorcyclistService {

    @Autowired
    private MotorcyclistRepository repository;

    public List<Motorcyclist> findByAvailable(GeoJsonPoint point, double radius ){

        List<Motorcyclist> allMotorcyclist =
                repository.findByLocationWithin(new Circle(point.getX(),point.getY(),radius));


        return allMotorcyclist.stream().filter(m-> m.getAvailable() == true).collect(Collectors.toList());
    }

    @Override
    public List<Motorcyclist> findAll() {
        return repository.findAll();
    }


}
