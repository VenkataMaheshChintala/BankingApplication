package com.example.banking.dto;

public class changeStatusRequest {

    long accountNumber;
    String status;

    public changeStatusRequest(long accountNumber, String status) {
        this.accountNumber = accountNumber;
        this.status = status;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
