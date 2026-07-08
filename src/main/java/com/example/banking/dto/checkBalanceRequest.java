package com.example.banking.dto;

public class checkBalanceRequest {

    long accountNumber;
    String password;

    public checkBalanceRequest() {
    }

    public checkBalanceRequest(long accountNumber, String password) {
        this.accountNumber = accountNumber;
        this.password = password;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
