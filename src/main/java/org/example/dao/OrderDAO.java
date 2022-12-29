package org.example.dao;

import org.example.model.Order;
import org.example.model.User;

import java.util.List;

public interface OrderDAO {

    void createOrder(Order order);

    void updateOrder(Order order);

    Order getOrder(long id);

    void deleteOrder(long id);

    List<Order> getOrders();

    List<Order> getOrdersByUser(User user);
}
