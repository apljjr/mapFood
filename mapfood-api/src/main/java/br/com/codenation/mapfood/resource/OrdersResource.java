package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Motorcyclist;
import br.com.codenation.mapfood.document.Order;
import br.com.codenation.mapfood.exception.InvalidOrderException;
import br.com.codenation.mapfood.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Orders", description = "Orders endpoints")
@RestController
@RequestMapping("/orders")
public class OrdersResource implements Resource<Order>{

    private final OrdersService ordersService;

    @Autowired
    public OrdersResource(OrdersService ordersService) {
        this.ordersService = ordersService;
    }


    @Override
    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return new ResponseEntity<>(ordersService.getAll(), HttpStatus.OK);
    }

    @Override
    @ApiOperation(value = "Find order by id")
    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable String id) {
        return new ResponseEntity<>(ordersService.getOne(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new order")
    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) throws InvalidOrderException {
        return new ResponseEntity<>(ordersService.save(order), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<List<Order>> processOrdersToDeliver(@RequestBody Motorcyclist motorcyclist) {
        return new ResponseEntity<>(ordersService.processOrdersToDeliver(motorcyclist), HttpStatus.OK);
    }
}
