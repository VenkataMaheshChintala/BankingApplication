package com.example.banking.dto;

import com.example.banking.entity.Account;

import java.util.List;

public class ListAccountResponse {

    String message;
    boolean status;
    List<accountDataForLoading> accounts;

    public ListAccountResponse() {
    }

    public ListAccountResponse(String message, boolean status, List<accountDataForLoading> accounts) {
        this.message = message;
        this.status = status;
        this.accounts = accounts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<accountDataForLoading> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<accountDataForLoading> accounts) {
        this.accounts = accounts;
    }
}
