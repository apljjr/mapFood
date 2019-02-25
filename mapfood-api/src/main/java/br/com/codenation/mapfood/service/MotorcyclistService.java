package br.com.codenation.mapfood.service;

import br.com.codenation.mapfood.document.Motorcyclist;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public interface MotorcyclistService {

    List<Motorcyclist> findAll();

    public Motorcyclist findById(String id);

    Motorcyclist findTheNearestMotorcyclist(GeoJsonPoint point, double distance);

    public void turnOnAvailable(Motorcyclist motorcyclist);

    public void turnOffAvailable(Motorcyclist motorcyclist);
}
