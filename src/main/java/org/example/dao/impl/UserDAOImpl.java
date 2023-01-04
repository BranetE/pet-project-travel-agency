package org.example.dao.impl;

import org.example.dao.UserDAO;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public User findUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User)session
                .createQuery("from User u where u.email = :email ")
                .setParameter("email", email)
                .uniqueResult();
        return user;
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        session.delete(user);
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User").list();
        return users;
    }

    @Override
    public Boolean existsByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session
                .createQuery("from User u where u.email = :email ")
                .setParameter("email", email)
                .list();
        return users.size() != 0;
    }

    @Override
    public Boolean existsByPhone(String phone) {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session
                .createQuery("from User u where u.phone = :phone ")
                .setParameter("phone", phone)
                .list();
        return users.size() != 0;
    }

}
