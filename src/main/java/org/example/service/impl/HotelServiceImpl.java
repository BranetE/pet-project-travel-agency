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
    public void createHotel(Hotel hotel) {
        hotelDAO.createHotel(hotel);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        hotelDAO.updateHotel(hotel);
    }

    @Override
    public Hotel getHotel(long id) {
        return hotelDAO.getHotel(id);
    }

    @Override
    public void deleteHotel(long id) {
        hotelDAO.deleteHotel(id);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelDAO.findAll();
    }

    @Override
    public List<Hotel> getAllHotelsByCountry(String country) {
        return hotelDAO.findAllByCountry(country);
    }
}
