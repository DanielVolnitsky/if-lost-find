package com.daniel.iflostfind.exception;

public class UserAlreadyExistsExeption extends RuntimeException {

    public UserAlreadyExistsExeption() {
    }

    public UserAlreadyExistsExeption(String message) {
        super(message);
    }
}
