package org.example.dao.impl;

import org.example.dao.OrderDAO;
import org.example.model.Order;
import org.example.model.Room;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
        List<Order> orders = session.createQuery("from Order o where o.user=user").list();
        return orders;
    }


//    public List<LocalDate> empty(Room room) {
//        Session session = sessionFactory.getCurrentSession();
//        List<LocalDate> dates = session.createQuery("select startTime, endTime from Order o where o.room = room").list();
//        return dates;
//    }
}
