package br.com.codenation.mapfood.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private LocalDateTime timestamp;
    private List<OrderItem> items;
    private OrderStatus status;
}
