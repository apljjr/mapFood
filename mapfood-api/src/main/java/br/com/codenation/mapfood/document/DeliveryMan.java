package br.com.codenation.mapfood.document;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Data
@Document
public class DeliveryMan {

    @Id
    private String _id;
    private GeoJsonPoint location;
    private String original_id;

}
