package br.com.codenation.mapfood.service.impl;

import br.com.codenation.mapfood.service.RouteService;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private static final String API_KEY = "AIzaSyCH0kAoRhLam7se09583UaYBZNT2Gych3k";

    private static final String URL_MAPS = "https://maps.googleapis.com/maps/api/directions/json?";

    @Override
    public String callService(GeoJsonPoint motorclycleLocation, GeoJsonPoint userLocation, List<GeoJsonPoint> restautantLocation){
        String url = generateURL(motorclycleLocation, userLocation, restautantLocation);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response.getBody();
    }

    private String generateURL(GeoJsonPoint origin, GeoJsonPoint destiny, List<GeoJsonPoint> restautantLocation){
        StringBuilder sb = new StringBuilder();

        sb.append(URL_MAPS);
        sb.append("origin=");
        sb.append(origin.getY());
        sb.append(",");
        sb.append(origin.getX());
        sb.append("&destiny=");
        sb.append(destiny.getY());
        sb.append(",");
        sb.append("&waypoints=");

        if (restautantLocation.size() > 1){
            for (int i = 0; i < restautantLocation.size(); i++) {
                if (i == 0) {
                    sb.append("via:");
                    sb.append(restautantLocation.get(i).getY());
                    sb.append(",");
                    sb.append(restautantLocation.get(i).getX());
                }

                sb.append("|via:");
                sb.append(restautantLocation.get(i).getY());
                sb.append(",");
                sb.append(restautantLocation.get(i).getX());
            }
        }

        sb.append("&key=" + API_KEY);

        return sb.toString();
    }

}
