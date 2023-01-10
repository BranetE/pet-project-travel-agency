package org.example.service.impl;

import org.example.dao.OrderDAO;
import org.example.exception.RoomIsNotAvailableException;
import org.example.model.Order;
import org.example.model.Room;
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

        if(checkIfBusy(order))
        {
            throw new RoomIsNotAvailableException("Room is not available for these dates", order.getRoom());
        }else {
            orderDAO.create(order);
        }
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

    public List<String[]> getAllBusyDates(long roomId){return orderDAO.findAllBusyDates(roomId);}

    private boolean checkIfBusy(Order order){
        Room room = order.getRoom();

        List<Order> orders = getAllOrders();
        orders = orders.stream().filter(o -> o.getRoom().equals(room)).toList();

        for (Order o: orders) {
            if(
                    (order.getStartTime().isAfter(o.getStartTime()) && order.getStartTime().isBefore(o.getEndTime())) ||
                    (order.getEndTime().isAfter(o.getStartTime()) && order.getEndTime().isBefore(o.getEndTime()))
            )
            {
                return true;
            }
        }
        return false;
    }
}
