package br.com.codenation.mapfood.service;

import br.com.codenation.mapfood.document.Motorcyclist;
import br.com.codenation.mapfood.document.Order;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public interface MotorcyclistService {

    List<Motorcyclist> findAll();

    Motorcyclist findById(String id);

    Motorcyclist findTheNearestMotorcyclist(GeoJsonPoint point, double distance);

    void turnOnAvailable(Motorcyclist motorcyclist);

    void turnOffAvailable(Motorcyclist motorcyclist);
}
