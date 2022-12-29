package org.example.service.impl;

import org.example.dao.HotelDAO;
import org.example.model.Hotel;
import org.example.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelDAO hotelDAO;

    @Override
    public void create(Hotel hotel) {
        hotelDAO.createHotel(hotel);
    }

    @Override
    public void update(Hotel hotel) {
        hotelDAO.updateHotel(hotel);
    }

    @Override
    public Hotel read(long id) {
        return hotelDAO.getHotel(id);
    }

    @Override
    public void delete(long id) {
        hotelDAO.deleteHotel(id);
    }

    @Override
    public List<Hotel> findAll() {
        return hotelDAO.getHotels();
    }

    @Override
    public List<Hotel> findAllByCountry(String country) {
        return hotelDAO.getHotelsByCountry(country);
    }
}
