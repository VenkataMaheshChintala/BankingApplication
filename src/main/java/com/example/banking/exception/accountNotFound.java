package com.example.banking.exception;

public class accountNotFound extends RuntimeException{

    public accountNotFound(String message) {
        super(message);
    }

}
