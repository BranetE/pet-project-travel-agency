package org.example.exception;

import org.example.model.Room;

public class RoomWithTheSameNumberExistsException extends RuntimeException{

    private Room room;
    private String method;

    public RoomWithTheSameNumberExistsException(String message, Room room, String method) {
        super(message);
        this.room = room;
        this.method = method;
    }

    public Room getRoom() {
        return room;
    }

    public String getMethod() {
        return method;
    }
}
