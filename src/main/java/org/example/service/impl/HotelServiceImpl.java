package org.example.service.impl;

import org.example.dao.HotelDAO;
import org.example.model.Hotel;
import org.example.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelDAO hotelDAO;

    @Override
//    @Transactional
    public void createHotel(Hotel hotel) {
        hotelDAO.createHotel(hotel);
    }

    @Override
//    @Transactional
    public void updateHotel(Hotel hotel) {
        hotelDAO.updateHotel(hotel);
    }

    @Override
//    @Transactional
    public Hotel getHotel(long id) {
        return hotelDAO.getHotel(id);
    }

    @Override
//    @Transactional
    public void deleteHotel(long id) {
        hotelDAO.deleteHotel(id);
    }

    @Override
//    @Transactional
    public List<Hotel> getAllHotels() {
        return hotelDAO.findAll();
    }

    @Override
//    @Transactional
    public List<Hotel> getAllHotelsByCountry(String country) {
        return hotelDAO.findAllByCountry(country);
    }
}
