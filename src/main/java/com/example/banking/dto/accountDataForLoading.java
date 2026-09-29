package com.example.banking.dto;

public class accountDataForLoading {

    long accountNumber;
    String accountType;
    String status;

    public accountDataForLoading() {
    }

    public accountDataForLoading(long accountNumber, String accountType, String status) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.status = status;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
