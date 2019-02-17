package br.com.codenation.mapfood.model;

import lombok.Data;

@Data
public class Localization {

    private String latitude;
    private String longitude;

    public Localization() {
    }

}
