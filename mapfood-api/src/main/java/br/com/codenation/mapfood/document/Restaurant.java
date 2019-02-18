package br.com.codenation.mapfood.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Restaurant {

    @Id
    private String itemId;
    private String itemDescription;
    private String restaurantId;
    private String restaurant;
    private String classification;
    private String unitPrice;
    private String addressCity;
}
