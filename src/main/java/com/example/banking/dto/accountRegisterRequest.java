package com.example.banking.dto;

public class accountRegisterRequest {

    String username;
    String accountType;

    public accountRegisterRequest() {
    }

    public accountRegisterRequest(String userName, String accountType) {
        this.username = userName;
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
