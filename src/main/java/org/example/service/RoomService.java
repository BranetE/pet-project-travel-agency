package org.example.service;

import org.example.model.Room;

import java.util.List;

public interface RoomService {
    void create(Room room);

    void update(Room room);

    Room read(long id);

    void delete(long id);

    List<Room> readAll();

    List<Room> readAllAvailable();
}
