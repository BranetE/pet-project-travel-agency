package org.example.dao;

import org.example.model.Order;

import java.util.List;

public interface OrderDAO {

    void createOrder(Order order);

    void updateOrder(Order order);

    Order getOrder(long id);

    void deleteOrder(long id);

    List<Order> getOrders();

    List<Order> getOrdersByUser(long userId);
}
