package com.example.banking.controller;

import com.example.banking.entity.Account;
import com.example.banking.entity.Transaction;
import com.example.banking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.banking.dto.depositWithdrawRequest;
import com.example.banking.service.accountService;
import com.example.banking.dto.accountNumberRequest;
import com.example.banking.dto.*;
import com.example.banking.service.userService;

import java.util.List;

import com.example.banking.dto.transferRequest;

@RestController
@RequestMapping("/accountapi")
public class accountController {

    private accountService accountService;
    private userService userService;

    public accountController() {}

    @Autowired
    public accountController(accountService accountService,userService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public accountRegisterResponse registerAccount(@RequestBody accountRegisterRequest request) {
        return accountService.registerAccount(request);
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

    @PostMapping("/checkbalance")
    public checkBalanceResponse checkBalance(@RequestBody accountNumberRequest request) { return accountService.checkBalance(request); }

    @PostMapping("/transfer")
    public transferResponse transferAmount(@RequestBody transferRequest request) {
        return accountService.transferAmount(request);
    }

    @PostMapping("/transactions")
    public List<Transaction> findAllTransactions(@RequestBody accountNumberRequest request) {
        return accountService.findByAccountNumber(request);
    }

    @PostMapping("/accounts")
    public usernameRequestResponse getAccounts(@RequestBody usernameRequest usernameRequest) {
        String username = usernameRequest.getUsername();
        User user = userService.findByUserName(username);
        List<Account> accounts = accountService.findByUserId(user.getUserId());
        usernameRequestResponse response = new usernameRequestResponse();
        response.setAccounts(accounts);
        response.setMessage("Accounts fetched successfully");
        response.setStatus(true);
        return response;
    }

}
