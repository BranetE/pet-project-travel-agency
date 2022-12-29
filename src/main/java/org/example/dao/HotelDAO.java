package org.example.dao;

import org.example.model.Hotel;

import java.util.List;

public interface HotelDAO {

    void createHotel(Hotel hotel);

    void updateHotel(Hotel hotel);

    Hotel getHotel(long id);

    void deleteHotel(long id);

    List<Hotel> getHotels();

    List<Hotel> getHotelsByCountry(String country);
}
