package br.com.codenation.mapfood.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api (value = "Teste", description = "API Teste")
@RequestMapping (value = "/teste")
public class Teste {

    @ApiOperation(value = "Retorna teste")
    @GetMapping (produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String teste () {
        return "Teste";
    }
}
