package com.example.banking.exception;

public class userIdNotFound extends RuntimeException {

    public userIdNotFound(String message) {
        super(message);
    }
}
