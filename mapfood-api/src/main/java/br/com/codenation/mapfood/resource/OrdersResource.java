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
public class OrdersResource {

    private final OrdersService ordersService;

    @Autowired
    public OrdersResource(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @ApiOperation(value = "Create a new order")
    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) throws InvalidOrderException {
        return new ResponseEntity<>(ordersService.save(order), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> list() {
        return new ResponseEntity<>(ordersService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Find order by id")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOne(@PathVariable String id) {
        return new ResponseEntity<>(ordersService.getOne(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<List<Order>> processOrdersToDeliver(@RequestBody Motorcyclist motorcyclist) {
        return new ResponseEntity<>(ordersService.processOrdersToDeliver(motorcyclist), HttpStatus.OK);
    }
}
