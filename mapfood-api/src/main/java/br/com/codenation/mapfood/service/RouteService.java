package br.com.codenation.mapfood.service;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public interface RouteService {
    String callService(GeoJsonPoint motorclycleLocation, GeoJsonPoint userLocation, List<GeoJsonPoint> restautantLocation);
}
