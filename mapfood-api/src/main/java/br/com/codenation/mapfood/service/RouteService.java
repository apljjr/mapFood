package br.com.codenation.mapfood.service;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class RouteService {

    private static final String API_KEY = "AIzaSyCH0kAoRhLam7se09583UaYBZNT2Gych3k";

    private static final String URL_MAPS = "https://maps.googleapis.com/maps/api/directions/json?";


    //Implementar algoritmo para realizar o cálculo de rota

    public static String generateURL(GeoJsonPoint origin, GeoJsonPoint destiny){

        String url = URL_MAPS.concat("origin=" + origin.toString());
        url = url.concat("&destiny=" + destiny.toString());

        return url.concat("&key=" + API_KEY);
    }

    //procurar motoqueiro, verificar o motoqueiro com menor raio para o restaurante


}
