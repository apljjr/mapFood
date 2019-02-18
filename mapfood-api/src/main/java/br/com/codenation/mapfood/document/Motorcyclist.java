package br.com.codenation.mapfood.document;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "products")
public class Motorcyclist {

    @Id
    private Long id;

    @Autowired
    private Localization localization;

    private Boolean available;

    public Motorcyclist() {
        this.available = true;
    }




}
