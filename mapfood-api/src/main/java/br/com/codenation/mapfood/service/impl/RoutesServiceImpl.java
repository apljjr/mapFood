package br.com.codenation.mapfood.service.impl;

import br.com.codenation.mapfood.document.Order;
import br.com.codenation.mapfood.service.RoutesService;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutesServiceImpl implements RoutesService {
    private final String API_KEY = "AIzaSyCH0kAoRhLam7se09583UaYBZNT2Gych3k";
    private GeoApiContext context;

    public RoutesServiceImpl() {
        this.context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
    }

    @Override
    public DirectionsResult getRoute(GeoJsonPoint origin, List<GeoJsonPoint> points) {
        List<DirectionsApiRequest.Waypoint> initialWaypoints = points.stream()
                .map(point -> {
                    Double lng = point.getCoordinates().get(0);
                    Double lat = point.getCoordinates().get(1);
                    return new DirectionsApiRequest.Waypoint(new LatLng(lat, lng));
                })
                .collect(Collectors.toList());
        GeoJsonPoint destination = points.get(0);

        LatLng originPoint = new LatLng(origin.getCoordinates().get(1), origin.getCoordinates().get(0));
        LatLng destinationPoint = new LatLng(destination.getCoordinates().get(1), destination.getCoordinates().get(0));

        List<DirectionsApiRequest.Waypoint> waypoints = new ArrayList<>();
        if (initialWaypoints.size() > 1) {
            waypoints = initialWaypoints.subList(1, initialWaypoints.size() - 1);
        }

        String originStr = originPoint.lat + "," + originPoint.lng;
        String destinationStr = destinationPoint.lat + "," + destinationPoint.lng;
        DirectionsResult directions = null;
        try {
            directions = DirectionsApi.getDirections(context, originStr, destinationStr)
                    .waypoints(waypoints.toArray(new DirectionsApiRequest.Waypoint[waypoints.size()]))
                    .mode(TravelMode.DRIVING)
                    .optimizeWaypoints(true)
                    .await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directions;
    }
}
