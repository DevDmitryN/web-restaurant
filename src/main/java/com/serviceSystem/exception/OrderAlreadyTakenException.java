package com.serviceSystem.exception;

public class OrderAlreadyTakenException extends RuntimeException {
    public OrderAlreadyTakenException() {
        super();
    }

    public OrderAlreadyTakenException(String message) {
        super(message);
    }

    public OrderAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderAlreadyTakenException(Throwable cause) {
        super(cause);
    }

    protected OrderAlreadyTakenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
