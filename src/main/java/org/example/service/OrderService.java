package org.example.service;

import org.example.model.Order;
import org.example.model.User;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);

    void updateOrder(Order order);

    Order getOrder(long id);

    void deleteOrder(long id);

    List<Order> getAllOrders();

    List<Order> getAllOrdersByUser(User user);

    List<String[]> getAllBusyDates(long roomId);
}
