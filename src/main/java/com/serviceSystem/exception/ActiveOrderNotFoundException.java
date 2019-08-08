package com.serviceSystem.exception;

public class ActiveOrderNotFoundException extends RuntimeException {
    public ActiveOrderNotFoundException(String message) {
        super(message);
    }
}
