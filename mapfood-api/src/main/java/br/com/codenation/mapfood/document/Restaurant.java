package br.com.codenation.mapfood.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Restaurant {

    @Id
    private String _id;
    private GeoJsonPoint location;
    private String restaurant;
    private String address_city;
    private String dish_description;
    private String original_id;
    private List<Item> items;
}