package org.example.exception;

import org.example.model.Room;

public class RoomIsNotAvailableException extends RuntimeException{

    private Room room;

    public RoomIsNotAvailableException() {
    }

    public RoomIsNotAvailableException(String message) {
        super(message);
    }

    public RoomIsNotAvailableException(String message, Room room) {
        this(message);
        setRoom(room);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
