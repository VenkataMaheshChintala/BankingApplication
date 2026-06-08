package com.example.banking.exception;

public class accountCantBeCreated extends RuntimeException{

    public accountCantBeCreated(String message) {
        super(message);
    }

}
