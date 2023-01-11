package org.example.service.impl;

import com.mchange.util.AlreadyExistsException;
import org.example.dao.HotelDAO;
import org.example.exception.NullEntityReferenceException;
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
    public void createHotel(Hotel hotel) throws AlreadyExistsException{
        if(hotel == null){
            throw new NullEntityReferenceException("Entity hotel is null in create method");
        }
        if(hotelDAO.findHotelsByName(hotel.getName()).size() != 0){
            List<Hotel> hotels = hotelDAO.findHotelsByName(hotel.getName());
            for (Hotel hotel1 : hotels){
                if(hotel1.getCountry().equals(hotel.getCountry())){
                    throw new AlreadyExistsException("Hotel with same name exists in this country");
                }
            }
        }
        hotelDAO.createHotel(hotel);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        if(hotel == null){
            throw new NullEntityReferenceException("Entity hotel is null in update method");
        }
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
