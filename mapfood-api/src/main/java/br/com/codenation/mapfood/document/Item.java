package br.com.codenation.mapfood.document;

import lombok.Data;

@Data
public class Item {

    private String id;
    private String item_description;
    private String classification;
    private String unit_price;

}
