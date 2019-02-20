package br.com.codenation.mapfood.service;

import br.com.codenation.mapfood.document.Motorcyclist;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public interface MotorcyclistService {

    List<Motorcyclist> findAllNearByAvailable(GeoJsonPoint point, double distance);

    List<Motorcyclist> findAll();

    void save(Motorcyclist motorcyclist);

    Motorcyclist findById(String id);
}
