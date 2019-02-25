package br.com.codenation.mapfood.service;

import com.google.maps.model.DirectionsResult;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public interface RoutesService {
    DirectionsResult getRoute(GeoJsonPoint origin, List<GeoJsonPoint> points);
}
