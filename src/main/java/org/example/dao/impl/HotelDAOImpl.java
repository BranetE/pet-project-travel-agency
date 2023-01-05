package org.example.dao.impl;

import org.example.dao.HotelDAO;
import org.example.model.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class HotelDAOImpl implements HotelDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void createHotel(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        session.save(hotel);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        session.update(hotel);
    }

    @Override
    public Hotel getHotel(long id) {
        Session session = sessionFactory.getCurrentSession();
        Hotel hotel = session.get(Hotel.class, id);
        return hotel;
    }

    @Override
    public void deleteHotel(long id) {
        Session session = sessionFactory.getCurrentSession();
        Hotel hotel = session.get(Hotel.class, id);
        session.delete(hotel);
    }

    @Override
    public List<Hotel> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Hotel> hotels = session.createQuery("from Hotel").list();
        return hotels;
    }

    @Override
    public List<Hotel> findAllByCountry(String country) {
        Session session = sessionFactory.getCurrentSession();
        List<Hotel> hotels = session.createQuery("from Hotel h where h.country=country").list();
        return hotels;
    }
}
