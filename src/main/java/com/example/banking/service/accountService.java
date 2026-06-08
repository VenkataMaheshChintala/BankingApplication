package com.example.banking.service;

import com.example.banking.dto.transferRequest;
import com.example.banking.dto.depositWithdrawRequest;
import com.example.banking.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;
import com.example.banking.dto.accountNumberRequest;
import com.example.banking.dto.changeStatusRequest;
import com.example.banking.dto.*;

public interface accountService {

    int countByUserIdAndAccountType(long userId,String accountType);

    accountRegisterResponse registerAccount(accountRegisterRequest request);

    void deposit(depositWithdrawRequest request);

    void withdraw(depositWithdrawRequest request);

    checkBalanceResponse checkBalance(accountNumberRequest request);

    transferResponse transferAmount(transferRequest transferRequest);

    List<Transaction> findByAccountNumber(accountNumberRequest request);

    void changeStatus(changeStatusRequest request);

}
