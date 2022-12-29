package org.example.dao.impl;

import org.example.dao.HotelDAO;
import org.example.model.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelDAOImpl implements HotelDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void createHotel(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(hotel);
        session.getTransaction().commit();
    }

    @Override
    public void updateHotel(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(hotel);
        session.getTransaction().commit();
    }

    @Override
    public Hotel getHotel(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Hotel hotel = session.get(Hotel.class, id);
        session.getTransaction().commit();
        return hotel;
    }

    @Override
    public void deleteHotel(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Hotel hotel = session.get(Hotel.class, id);
        session.delete(hotel);
        session.getTransaction().commit();
    }

    @Override
    public List<Hotel> getHotels() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Hotel> hotels = session.createQuery("from Hotel").list();
        session.getTransaction().commit();
        return hotels;
    }

    @Override
    public List<Hotel> getHotelsByCountry(String country) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Hotel> hotels = session.createQuery("from Hotel h where h.country=country").list();
        session.getTransaction().commit();
        return hotels;
    }
}
