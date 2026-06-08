package com.example.banking.dto;

import java.math.BigDecimal;

public class depositWithdrawRequest {

    long accountNumber;
    BigDecimal amount;

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
