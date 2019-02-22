package br.com.codenation.mapfood.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class OrderItem {
    @DBRef
    private Item item;
    private Integer amount;
}
