package com.example.banking.dto;

import java.math.BigDecimal;

public class checkBalanceResponse {

    boolean status;
    String message;
    BigDecimal balance;

    public checkBalanceResponse() {
    }

    public checkBalanceResponse(boolean status, String message, BigDecimal balance) {
        this.status = status;
        this.message = message;
        this.balance = balance;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
