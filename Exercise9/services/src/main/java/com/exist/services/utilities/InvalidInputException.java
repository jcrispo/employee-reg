package com.exist.services.utilities;

public class InvalidInputException extends Exception {
    private String message;

    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
