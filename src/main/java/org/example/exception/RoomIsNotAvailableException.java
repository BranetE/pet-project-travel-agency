package org.example.exception;

import org.example.model.Order;

public class RoomIsNotAvailableException extends RuntimeException{

    private Order order;

    private String method;

    public RoomIsNotAvailableException(String message) {
        super(message);
    }

    public RoomIsNotAvailableException(String message, Order order, String method) {
        super(message);
        this.order = order;
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public Order getOrder() {
        return order;
    }

}
