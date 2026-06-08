package com.example.banking.service;

import com.example.banking.dto.transferRequest;
import com.example.banking.dto.depositWithdrawRequest;
import com.example.banking.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;
import com.example.banking.dto.accountNumberRequest;
import com.example.banking.dto.changeStatusRequest;

public interface accountService {

    int countByUserIdAndAccountType(long userId,String accountType);

    void registerAccount(long userId,String accountType);

    void deposit(depositWithdrawRequest request);

    void withdraw(depositWithdrawRequest request);

    BigDecimal checkBalance(accountNumberRequest request);

    void transferAmount(transferRequest transferRequest);

    List<Transaction> findByAccountNumber(accountNumberRequest request);

    void changeStatus(changeStatusRequest request);

}
