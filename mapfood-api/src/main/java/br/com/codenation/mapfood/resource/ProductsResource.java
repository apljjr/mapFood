package br.com.codenation.mapfood.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api (value = "Itens", description = "Manipulating Itens")
@RestController
@RequestMapping (value = "/itens")
public class ProductsResource {


    @ApiOperation(value = "Find all the itens by restaurant")
    @GetMapping (value = "/findAll", produces = "application/json")
    public ResponseEntity<?> findAllItens () {
        try {
//            productsList = productsRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return new ResponseEntity<List>(productsList, HttpStatus.OK);
        return null;
    }


}
