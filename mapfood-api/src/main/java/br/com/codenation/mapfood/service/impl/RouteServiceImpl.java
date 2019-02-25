package br.com.codenation.mapfood.service.impl;

import br.com.codenation.mapfood.service.RouteService;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private static final String API_KEY = "AIzaSyCH0kAoRhLam7se09583UaYBZNT2Gych3k";

    private static final String URL_MAPS = "https://maps.googleapis.com/maps/api/directions/json?";

//    @Autowired
//    private MotorcyclistService motorcyclistService;
//    private RestaurantService restaurantService;

    //Implementar algoritmo para realizar o cálculo de rota

    @Override
    public String callService(GeoJsonPoint motorclycleLocation, GeoJsonPoint userLocation, List<GeoJsonPoint> restautantLocation){
        String url = generateURL(motorclycleLocation, userLocation, restautantLocation);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);

        return response.getBody();
    }

    private String generateURL(GeoJsonPoint origin, GeoJsonPoint destiny, List<GeoJsonPoint> restautantLocation){


        String url = URL_MAPS.concat("origin=" + origin.getY() + "," + origin.getX()) +
                "&destiny=" + destiny.getY() + "," + destiny.getX()
                +"&waypoints=" ;
        if(restautantLocation.size()>1){
            for (int i = 0; i < restautantLocation.size(); i++) {
                if(i==0) {
                    String via = "via:" + restautantLocation.get(i).getY() + "," + restautantLocation.get(i).getX();
                    url = url.concat(via);
                }
                String via = "|via:" + restautantLocation.get(i).getY() + "," + restautantLocation.get(i).getX();
                url = url.concat(via);
            }
        }
        return url.concat("&key=" + API_KEY);
    }

}
