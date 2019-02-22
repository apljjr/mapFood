package br.com.codenation.mapfood.resource;

import br.com.codenation.mapfood.document.Order;
import br.com.codenation.mapfood.exception.InvalidOrderException;
import br.com.codenation.mapfood.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersResource {

    private final OrdersService ordersService;

    @Autowired
    public OrdersResource(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) throws InvalidOrderException {
        return new ResponseEntity<>(ordersService.save(order), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> list() {
        return new ResponseEntity<>(ordersService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOne(@PathVariable String id) {
        return new ResponseEntity<>(ordersService.getOne(id), HttpStatus.OK);
    }
}
