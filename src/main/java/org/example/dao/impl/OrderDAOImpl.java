package org.example.dao.impl;

import org.example.dao.OrderDAO;
import org.example.model.Order;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void create(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
    }

    @Override
    @Transactional
    public void update(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.update(order);
    }

    @Override
    @Transactional
    public Order read(long id) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, id);
        return order;
    }

    @Override
    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, id);
        session.delete(order);
    }

    @Override
    @Transactional
    public List<Order> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Order> orders = session.createQuery("from Order").list();
        return orders;
    }

    @Override
    @Transactional
    public List<Order> findAllByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        List<Order> orders = session.createQuery("from Order o where o.user=:user").setParameter("user", user).list();
        return orders;
    }

    @Transactional
    public List<Order> findAllByRoom(long roomId){
        Session session = sessionFactory.getCurrentSession();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        List<Order> orders = session.createQuery("from Order where room.id=:roomId").setParameter("roomId", roomId).list();
        orders= orders.stream().sorted(Comparator.comparing(Order::getStartTime).thenComparing(Order::getEndTime)).toList();
        return orders;
    }

}
