package org.example.dao.impl;

import org.example.dao.RoomDAO;
import org.example.model.Hotel;
import org.example.model.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomDAOImpl implements RoomDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void create(Room room) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(room);
        session.getTransaction().commit();
    }

    @Override
    public void update(Room room) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(room);
        session.getTransaction().commit();
    }

    @Override
    public Room read(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Room room = session.get(Room.class, id);
        session.getTransaction().commit();
        return room;
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Room room = session.get(Room.class, id);
        session.delete(room);
        session.getTransaction().commit();
    }

    @Override
    public List<Room> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Room> rooms = session.createQuery("from Room").list();
        session.getTransaction().commit();
        return rooms;
    }

    @Override
    public List<Room> findAvailable(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Room> rooms = session.createQuery("from Room r where r.hotel=hotel and r not in (select o.rooms from Order o)").list();
        session.getTransaction().commit();
        return rooms;    }
}