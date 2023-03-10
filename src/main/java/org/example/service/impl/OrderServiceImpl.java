package org.example.service.impl;

import org.example.dao.OrderDAO;
import org.example.exception.InappropriateDateException;
import org.example.exception.NullEntityReferenceException;
import org.example.exception.RoomIsNotAvailableException;
import org.example.model.Order;
import org.example.model.Room;
import org.example.model.User;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO orderDAO;

    @Override
    public void createOrder(Order order) {
        if(order == null){
            throw new NullEntityReferenceException("Entity order is null in create method");
        }

        if(order.getStartTime().isAfter(order.getEndTime())){
            throw new InappropriateDateException("You cannot check out before you check in!", order, "create");
        }

        if(order.getStartTime().isBefore(LocalDate.now())){
            throw new InappropriateDateException("You cannot book room for a past date", order, "create");
        }

        if(checkIfBusy(order))
        {
            throw new RoomIsNotAvailableException("Room is not available for these dates", order, "create");
        }else {
            orderDAO.create(order);
        }
    }

    @Override
    public void updateOrder(Order order) {
        if(order == null){
            throw new NullEntityReferenceException("Entity order is null in update method");
        }

        if(order.getStartTime().isAfter(order.getEndTime())){
            throw new InappropriateDateException("You cannot check out before you check in!", order, "update");
        }

        if(order.getStartTime().isBefore(LocalDate.now())){
            throw new InappropriateDateException("You cannot book room for a past date", order, "update");
        }

        if(checkIfBusy(order))
        {
            throw new RoomIsNotAvailableException("Room is not available for these dates", order, "update");
        }else {
            orderDAO.update(order);
        }
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
        if(user == null){
            throw new NullEntityReferenceException("Entity user is null in getAllOrdersByUser method");
        }
        return orderDAO.findAllByUser(user);
    }

    public List<Order> getAllByRoom(long roomId){return orderDAO.findAllByRoom(roomId);}

    private boolean checkIfBusy(Order order){
        if(order == null){
            throw new NullEntityReferenceException("Entity order is null in checkIfBusy method");
        }

        Room room = order.getRoom();

        List<Order> orders = getAllOrders();
        orders.stream().forEach( o -> System.out.println(o.getId()));
        orders = orders.stream()
                .filter(o -> o.getId() != order.getId())
                .filter(o -> o.getRoom().getId() == room.getId())
                .toList();

        for (Order o: orders) {
            if(
                    (order.getStartTime().isAfter(o.getStartTime()) && order.getStartTime().isBefore(o.getEndTime())) ||
                    (order.getEndTime().isAfter(o.getStartTime()) && order.getEndTime().isBefore(o.getEndTime())) ||
                            order.getStartTime().isEqual(o.getStartTime()) || order.getStartTime().isEqual(o.getEndTime()) ||
                            order.getEndTime().isEqual(o.getStartTime()) || order.getEndTime().isEqual(o.getEndTime())
            )
            {
                return true;
            }
        }
        return false;
    }
}
