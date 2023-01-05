package org.example.service;

import org.example.model.Hotel;
import org.example.model.Room;

import java.util.List;

public interface RoomService {
    void createRoom(Room room);

    void updateRoom(Room room);

    Room getRoom(long id);

    void deleteRoom(long id);

    List<Room> getAllRooms();

    List<Room> getAllRoomsByHotel(Hotel hotel);
}
