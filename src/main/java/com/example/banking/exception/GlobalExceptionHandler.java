package com.example.banking.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(userIdNotFound.class)
    public String userNotFound(userIdNotFound exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(accountNotFound.class)
    public String accountNotFound(accountNotFound exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(accountCantBeCreated.class)
    public String accountCantBeCreated(accountCantBeCreated exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(unableToDepositWithdraw.class)
    public String unableToDeposit(unableToDepositWithdraw exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(insufficientFunds.class)
    public String unableToDeposit(insufficientFunds exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(incorrectPassword.class)
    public String incorrectPassword(incorrectPassword exception) {
        return exception.getMessage();
    }

}
