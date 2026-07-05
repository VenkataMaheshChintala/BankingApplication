package com.example.banking.dto;

import com.example.banking.entity.Account;

import java.util.List;

public class usernameRequestResponse {

    boolean status;
    String message;
    List<Account> accounts;

    public usernameRequestResponse() {
    }

    public usernameRequestResponse(boolean status, String message, List<Account> accounts) {
        this.status = status;
        this.message = message;
        this.accounts = accounts;
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
