package org.example.service.impl;

import org.example.dao.RoomDAO;
import org.example.exception.RoomWithTheSameNumberExistsException;
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
        if(checkIfRoomAlreadyExists(room)){
            throw new RoomWithTheSameNumberExistsException("Room with the same number already exists", room, "create");
        }

        roomDAO.create(room);
    }

    @Override
    public void updateRoom(Room room) {
        if(checkIfRoomAlreadyExists(room)){
            throw new RoomWithTheSameNumberExistsException("Room with the same number already exists", room, "update");
        }

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
    public List<Room> getAllRoomsByHotel(Hotel hotel) {
        return roomDAO.findAllByHotel(hotel);
    }

    private boolean checkIfRoomAlreadyExists(Room room){
        List<Long> numbers = getAllRoomsByHotel(room.getHotel()).stream()
                .filter(r -> r.getId() != room.getId())
                .map(r -> r.getNumber())
                .toList();
        if(numbers.contains(Long.valueOf(room.getNumber()))){
            return true;
        }else {
            return false;
        }
    }
}
