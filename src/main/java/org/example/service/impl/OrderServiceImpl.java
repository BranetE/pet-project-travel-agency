package org.example.service.impl;

import org.example.dao.OrderDAO;
import org.example.model.Order;
import org.example.model.User;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO orderDAO;

    @Override
    public void createOrder(Order order) {
        orderDAO.create(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.update(order);
    }

    @Override
    public Order getOrder(long id) {
        return orderDAO.read(id);
    }

    @Override
    public void deleteOrder(long id) {
        orderDAO.delete(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    @Override
    public List<Order> getAllOrdersByUser(User user) {
        return orderDAO.findAllByUser(user);
    }
}
