package br.com.codenation.mapfood.document;

import lombok.Data;

import java.util.List;

@Data
public class Location {

    private List<String> coordinates;
    private String type;
}
