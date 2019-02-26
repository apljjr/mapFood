package br.com.codenation.mapfood.service;

import br.com.codenation.mapfood.document.Motorcyclist;
import br.com.codenation.mapfood.document.Order;

import java.util.List;

public interface OrdersService {

    Order save(Order order);

    List<Order> getAll();

    Order getOne(String id);

    List<Order> processOrdersToDeliver(Motorcyclist motorcyclist);
}
