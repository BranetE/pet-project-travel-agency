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
    public void create(Room room) {
        roomDAO.createRoom(room);
    }

    @Override
    public void update(Room room) {
        roomDAO.updateRoom(room);
    }

    @Override
    public Room read(long id) {
        return roomDAO.getRoom(id);
    }

    @Override
    public void delete(long id) {
        roomDAO.deleteRoom(id);
    }

    @Override
    public List<Room> findAll() {
        return roomDAO.getRooms();
    }

    @Override
    public List<Room> findAllAvailable(Hotel hotel) {
        return roomDAO.getAvailableRooms(hotel);
    }
}
