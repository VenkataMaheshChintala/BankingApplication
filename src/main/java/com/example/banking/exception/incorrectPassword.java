package com.example.banking.exception;

public class incorrectPassword extends RuntimeException{

    public incorrectPassword(String message) {
        super(message);
    }

}
