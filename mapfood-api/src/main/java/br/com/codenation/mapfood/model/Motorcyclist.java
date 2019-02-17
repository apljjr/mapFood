package br.com.codenation.mapfood.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

@Data
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
