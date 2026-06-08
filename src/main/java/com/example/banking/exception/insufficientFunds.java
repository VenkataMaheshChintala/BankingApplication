package com.example.banking.exception;

public class insufficientFunds extends RuntimeException{

    public insufficientFunds(String message) {
        super(message);
    }

}
