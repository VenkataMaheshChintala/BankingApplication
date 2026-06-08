package com.example.banking.exception;

public class unableToDepositWithdraw extends RuntimeException{

    public unableToDepositWithdraw(String message) {
        super(message);
    }

}
