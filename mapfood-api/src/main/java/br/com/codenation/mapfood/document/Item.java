package br.com.codenation.mapfood.document;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Item {

    private ObjectId _id;
    private String item_description;
    private String classification;
    private String unit_price;

}
