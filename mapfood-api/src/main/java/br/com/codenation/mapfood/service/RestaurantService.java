package br.com.codenation.mapfood.service;

import br.com.codenation.mapfood.document.DeliveryMan;
import br.com.codenation.mapfood.document.Restaurant;
import br.com.codenation.mapfood.repository.DeliveryManRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private DeliveryManRepository deliveryManRepository;

    public List<DeliveryMan> searchDeliveryMan(Restaurant restaurant){
        final GeoJsonPoint location = restaurant.getLocation();


        return deliveryManRepository.findByLocationNear(location, new Distance(5));
    }
}
