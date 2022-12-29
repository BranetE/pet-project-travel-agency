package org.example.service;

import org.example.model.Hotel;

import java.util.List;

public interface HotelService {
    void create(Hotel hotel);

    void update(Hotel hotel);

    Hotel read(long id);

    void delete(long id);

    List<Hotel> findAll();

    List<Hotel> findAllByCountry(String country);
}
