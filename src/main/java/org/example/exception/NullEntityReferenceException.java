package org.example.exception;

import org.example.model.Order;

public class NullEntityReferenceException extends RuntimeException{

    public NullEntityReferenceException(String message){
        super(message);
    }
}
