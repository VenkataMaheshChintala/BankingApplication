package com.example.banking.dto;

public class accountNumberRequest {

    long accountNumber;

    public accountNumberRequest(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
