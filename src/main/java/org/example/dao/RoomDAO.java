package org.example.dao;

import org.example.model.Hotel;
import org.example.model.Room;

import java.util.List;

public interface RoomDAO {

    void createRoom(Room room);

    void updateRoom(Room room);

    Room getRoom(long id);

    void deleteRoom(long id);

    List<Room> getRooms();

    List<Room> getAvailableRooms(Hotel hotel);
}
