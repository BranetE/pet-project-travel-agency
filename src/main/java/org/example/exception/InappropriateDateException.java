package org.example.exception;

import org.example.model.Order;

public class InappropriateDateException extends RuntimeException{

    private Order order;

    private String method;

    public InappropriateDateException(String message, Order order, String method) {
        super(message);
        this.order = order;
        this.method = method;
    }

    public Order getOrder() {
        return order;
    }

    public String getMethod() {
        return method;
    }
}
