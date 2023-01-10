package org.example.dao.impl;

import org.example.dao.RoomDAO;
import org.example.model.Hotel;
import org.example.model.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RoomDAOImpl implements RoomDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void create(Room room) {
        Session session = sessionFactory.getCurrentSession();
        session.save(room);
    }

    @Override
    @Transactional
    public void update(Room room) {
        Session session = sessionFactory.getCurrentSession();
        session.update(room);
    }

    @Override
    @Transactional
    public Room read(long id) {
        Session session = sessionFactory.getCurrentSession();
        Room room = session.get(Room.class, id);
        return room;
    }

    @Override
    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Room room = session.get(Room.class, id);
        session.delete(room);
    }

    @Override
    @Transactional
    public List<Room> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Room> rooms = session.createQuery("from Room").list();
        return rooms;
    }

    @Override
    @Transactional
    public List<Room> findAllByHotel(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        List<Room> rooms = session.createQuery("select r from Room r where r.hotel=:hotel")
                .setParameter("hotel", hotel)
                .list();
        return rooms;    }
}
