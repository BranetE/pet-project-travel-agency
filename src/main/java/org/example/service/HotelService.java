package org.example.service;

import org.example.model.Hotel;

import java.util.List;

public interface HotelService {
    void createHotel(Hotel hotel);

    void updateHotel(Hotel hotel);

    Hotel getHotel(long id);

    void deleteHotel(long id);

    List<Hotel> getAllHotels();

    List<Hotel> getAllHotelsByCountry(String country);
}
