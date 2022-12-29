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
    public void create(Order order) {
        orderDAO.createOrder(order);
    }

    @Override
    public void update(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public Order read(long id) {
        return orderDAO.getOrder(id);
    }

    @Override
    public void delete(long id) {
        orderDAO.deleteOrder(id);
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.getOrders();
    }

    @Override
    public List<Order> findAllByUser(User user) {
        return orderDAO.getOrdersByUser(user);
    }
}
