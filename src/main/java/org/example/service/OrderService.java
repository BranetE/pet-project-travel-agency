package org.example.service;

import org.example.model.Order;

import java.util.List;

public interface OrderService {
    void create(Order order);

    void update(Order order);

    Order read(long id);

    void delete(long id);

    List<Order> readAll();

    List<Order> readAllByUser();
}
