package org.example.service;

import com.mchange.util.AlreadyExistsException;
import org.example.model.Hotel;

import java.util.List;

public interface HotelService {
    void createHotel(Hotel hotel) throws AlreadyExistsException;

    void updateHotel(Hotel hotel);

    Hotel getHotel(long id);

    void deleteHotel(long id);

    List<Hotel> getAllHotels();

    List<Hotel> getAllHotelsByCountry(String country);
}
