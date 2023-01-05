package org.example.dao;

import org.example.model.Hotel;
import org.example.model.Room;

import java.util.List;

public interface RoomDAO {

    void create(Room room);

    void update(Room room);

    Room read(long id);

    void delete(long id);

    List<Room> findAll();

    List<Room> findAllByHotel(Hotel hotel);
}
