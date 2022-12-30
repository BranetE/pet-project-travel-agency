package org.example.service.impl;

import org.example.dao.RoomDAO;
import org.example.model.Hotel;
import org.example.model.Room;
import org.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomDAO roomDAO;

    @Override
    public void createRoom(Room room) {
        roomDAO.create(room);
    }

    @Override
    public void updateRoom(Room room) {
        roomDAO.update(room);
    }

    @Override
    public Room getRoom(long id) {
        return roomDAO.read(id);
    }

    @Override
    public void deleteRoom(long id) {
        roomDAO.delete(id);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDAO.findAll();
    }

    @Override
    public List<Room> getAllAvailableRooms(Hotel hotel) {
        return roomDAO.findAvailable(hotel);
    }
}
