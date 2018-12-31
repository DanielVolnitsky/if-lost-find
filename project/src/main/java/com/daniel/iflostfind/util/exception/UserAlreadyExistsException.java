package com.daniel.iflostfind.util.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
