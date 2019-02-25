package br.com.codenation.mapfood.service.impl;

import br.com.codenation.mapfood.document.Motorcyclist;
import br.com.codenation.mapfood.exception.MotorcyclistNotFoundException;
import br.com.codenation.mapfood.repository.MotorcyclistRepository;
import br.com.codenation.mapfood.service.MotorcyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorcyclistServiceImpl implements MotorcyclistService {
    private final MotorcyclistRepository motorcyclistRepository;

    @Autowired
    public MotorcyclistServiceImpl (MotorcyclistRepository motorcyclistRepository) {
        this.motorcyclistRepository = motorcyclistRepository;
    }

    public Motorcyclist findTheNearestMotorcyclist(GeoJsonPoint point, double distance) {
        List<Motorcyclist> allMotorcyclist =
                motorcyclistRepository.findByLocationNear(
                        new Point(point.getX(), point.getY()),
                        new Distance(distance, Metrics.KILOMETERS));
        return allMotorcyclist.stream()
                .filter(Motorcyclist::getAvailable).findFirst()
                .orElseThrow(MotorcyclistNotFoundException::new);
    }

    @Override
    public void turnOnAvailable(Motorcyclist motorcyclist){
        motorcyclist.setAvailable(true);
        motorcyclistRepository.save(motorcyclist);
    }

    @Override
    public void turnOffAvailable(Motorcyclist motorcyclist){
        motorcyclist.setAvailable(false);
        motorcyclistRepository.save(motorcyclist);
    }

    @Override
    public List<Motorcyclist> findAll() {
        return motorcyclistRepository.findAll();
    }

    @Override
    public Motorcyclist findById(String id) {
        return motorcyclistRepository.findById(id).orElseThrow(MotorcyclistNotFoundException::new);
    }


}
