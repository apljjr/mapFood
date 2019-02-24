package br.com.codenation.mapfood.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class OrderItem {
    private Item item;
    private Integer amount;
}
