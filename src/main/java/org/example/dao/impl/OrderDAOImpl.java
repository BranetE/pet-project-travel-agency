package org.example.dao.impl;

import org.example.dao.OrderDAO;
import org.example.model.Order;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void create(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
    }

    @Override
    public void update(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
    }

    @Override
    public Order read(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Order order = session.get(Order.class, id);
        session.getTransaction().commit();
        return order;
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Order order = session.get(Order.class, id);
        session.delete(order);
        session.getTransaction().commit();
    }

    @Override
    public List<Order> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Order> orders = session.createQuery("from Order").list();
        session.getTransaction().commit();
        return orders;
    }

    @Override
    public List<Order> findAllByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Order> orders = session.createQuery("from Order o where o.user=user").list();
        session.getTransaction().commit();
        return orders;
    }
}
