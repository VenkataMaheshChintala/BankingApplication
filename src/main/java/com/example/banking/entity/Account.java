package com.example.banking.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name="accounts")
public class Account {

    @Id
    @Column(name="account_number")
    long accountNumber;

    @Column(name="user_id")
    long userId;

    @Column(name="account_type")
    String accountType;

    @Column(name="balance")
    BigDecimal balance;

    @Column(name="status")
    String status;

    public Account() {
    }

    public Account(long accountNumber, long userId, String accountType) {
        this.accountNumber = accountNumber;
        this.userId = userId;
        this.accountType = accountType;
        this.balance = BigDecimal.ZERO;
        this.status = "ACTIVE";
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
