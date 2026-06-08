package com.example.banking.controller;

import com.example.banking.entity.Account;
import com.example.banking.entity.Transaction;
import org.springframework.web.bind.annotation.*;
import com.example.banking.dto.depositWithdrawRequest;
import com.example.banking.service.accountService;
import com.example.banking.dto.accountNumberRequest;

import java.math.BigDecimal;
import java.util.List;

import com.example.banking.dto.transferRequest;

@RestController
@RequestMapping("/accountapi")
public class accountController {

    private accountService accountService;

    public accountController(accountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public void registerAccount(@RequestBody Account account) {
        accountService.registerAccount(account.getUserId(),account.getAccountType());
    }

    @GetMapping("/test")
    public String test() {
        return "Accounts API is working fine!";
    }

    @PostMapping("/deposit")
    public void depositAmount(@RequestBody depositWithdrawRequest request) {
        accountService.deposit(request);
    }

    @PostMapping("/withdraw")
    public void withdrawAmount(@RequestBody depositWithdrawRequest request) { accountService.withdraw(request); }

    @PostMapping("/checkBalance")
    public BigDecimal checkBalance(@RequestBody accountNumberRequest request) { return accountService.checkBalance(request); }

    @PostMapping("/transfer")
    public void transferAmount(@RequestBody transferRequest request) {
        accountService.transferAmount(request);
    }

    @PostMapping("/transactions")
    public List<Transaction> findAllTransactions(@RequestBody accountNumberRequest request) {
        return accountService.findByAccountNumber(request);
    }



}
