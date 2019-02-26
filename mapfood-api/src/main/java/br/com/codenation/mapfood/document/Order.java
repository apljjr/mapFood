package br.com.codenation.mapfood.document;

import com.google.maps.model.DirectionsRoute;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
public class Order {

    @Id
    private String id;

    @DBRef
    private Restaurant restaurant;

    @DBRef
    private User user;

    private List<OrderItem> items;
    private OrderStatus status;
    private LocalDateTime timestamp;
    private Motorcyclist motorcyclist;
    private DirectionsRoute route;
    private Duration deliveryTime;
    private Long totalDistanceInMeters;
}
