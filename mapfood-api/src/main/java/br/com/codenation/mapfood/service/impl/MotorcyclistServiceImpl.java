package br.com.codenation.mapfood.service.impl;

import br.com.codenation.mapfood.document.Motorcyclist;
import br.com.codenation.mapfood.repository.MotorcyclistRepository;
import br.com.codenation.mapfood.service.MotorcyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotorcyclistServiceImpl implements MotorcyclistService {

    @Autowired
    private MotorcyclistRepository repository;

    public List<Motorcyclist> findAllNearByAvailable(GeoJsonPoint point, double distance) {


        List<Motorcyclist> allMotorcyclist =
                repository.findByLocationNear(new Point(point.getX(), point.getY()), new Distance(distance, Metrics.KILOMETERS));


        return allMotorcyclist.stream().filter(m -> m.getAvailable() == true).collect(Collectors.toList());

    }

    @Override
    public List<Motorcyclist> findAll() {

        return repository.findAll();
    }

    @Override
    public void save(Motorcyclist motorcyclist) {
        repository.save(motorcyclist);
    }

    @Override
    public Motorcyclist findById(String id) {
        return repository.findById(id).get();
    }


}
