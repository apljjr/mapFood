package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Restaurant;
import br.com.codenation.mapfood.repository.RestaurantsRepository;
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
public class ItemsResource {

    @Autowired
    private RestaurantsRepository restaurantsRepository;

    @ApiOperation(value = "Find all the itens by restaurant")
    @GetMapping (value = "/findAllItensByRestaurant/{idRestaurant}", produces = "application/json")
    public ResponseEntity<?> findAllItensByRestaurant (@PathVariable("idRestaurant") String idRestaurant) {
        Restaurant restaurant = new Restaurant();
        try {
            restaurant = restaurantsRepository.findItemsByRestanrant(idRestaurant);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
    }


}
