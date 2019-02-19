package br.com.codenation.mapfood.document;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "deliverymen")
public class Motorcyclist {

    @Id
    private ObjectId _id;
    private GeoJsonPoint location;

    private String original_id;

    //private Boolean available;

    public Motorcyclist() {
       // this.available = true;
    }

}
