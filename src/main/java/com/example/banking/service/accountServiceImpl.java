package com.example.banking.service;

import com.example.banking.dto.changeStatusRequest;
import com.example.banking.dto.transferRequest;
import com.example.banking.entity.Account;
import com.example.banking.entity.Transaction;
import com.example.banking.exception.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.banking.repository.accountRepository;
import com.example.banking.dto.depositWithdrawRequest;
import com.example.banking.repository.transactionRepository;

import java.math.BigDecimal;
import java.util.List;
import com.example.banking.dto.accountNumberRequest;

@Service
public class accountServiceImpl implements accountService{

    accountRepository accountRepository;
    transactionRepository transactionRepository;
    userService userService;


    public accountServiceImpl(accountRepository accountRepository,userService userService,transactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.userService = userService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public int countByUserIdAndAccountType(long userId, String accountType) {
        return accountRepository.countByUserIdAndAccountType(userId,accountType);
    }

    @Override
    @Transactional
    public void registerAccount(long userId, String accountType) {
        if(userService.findByUserId(userId) != null) {
            if(accountRepository.countByUserIdAndAccountType(userId,accountType) >= 2 && accountType.equals("SAVINGS")) {
                throw new accountCantBeCreated("Account cant be created as you already have 2 open savings accounts");
            } else if(accountRepository.countByUserIdAndAccountType(userId,accountType) >= 1 && accountType.equals("CURRENT")) {
                throw new accountCantBeCreated("Account cant be created as you already have 1 open current accounts");
            } else {
                if(accountRepository.count() == 0) {
                    Account acc = new Account(1000000001,userId,accountType);
                    accountRepository.save(acc);
                } else {
                    Account acc = new Account(accountRepository.getMaxAccountNumber()+1,userId,accountType);
                    accountRepository.save(acc);
                }
            }
        } else {
            throw new userIdNotFound("No user found with userId : " + userId);
        }
    }

    @Override
    @Transactional
    public void deposit(depositWithdrawRequest request) {
        Account acc = accountRepository.findByAccountNumber(request.getAccountNumber());
        if(acc != null) {
            if(acc.getStatus().equals("ACTIVE")) {
                if (request.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                    Transaction transaction = new Transaction(request.getAccountNumber(), "DEPOSIT", request.getAmount());
                    BigDecimal currentBalance = acc.getBalance();
                    acc.setBalance(currentBalance.add(request.getAmount()));
                    accountRepository.save(acc);
                    transactionRepository.save(transaction);
                } else {
                    throw new unableToDepositWithdraw("Can't deposit negative amount and 0");
                }
            } else {
                throw new unableToDepositWithdraw("Unable to deposit money as account is not active ");
            }
        } else {
            throw new accountNotFound("No account found with the accountNumber : " + request.getAccountNumber());
        }
    }

    @Override
    @Transactional
    public void withdraw(depositWithdrawRequest request) {
        Account acc = accountRepository.findByAccountNumber(request.getAccountNumber());
        if(acc != null) {
            if(acc.getStatus().equals("ACTIVE")) {
                if (request.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                    BigDecimal currentBalance = acc.getBalance();
                    if (currentBalance.compareTo(request.getAmount()) < 0) {
                        throw new insufficientFunds("Insufficient funds to withdraw amount");
                    } else {
                        Transaction transaction = new Transaction(request.getAccountNumber(), "WITHDRAW", request.getAmount());
                        acc.setBalance(currentBalance.subtract(request.getAmount()));
                        accountRepository.save(acc);
                        transactionRepository.save(transaction);
                    }
                } else {
                    throw new unableToDepositWithdraw("Can't withdraw negative amount and 0");
                }
            } else {
                throw new unableToDepositWithdraw("Unable to withdraw money as account is not active ");
            }
        } else {
            throw new accountNotFound("No account found with the accountNumber : " + request.getAccountNumber());
        }
    }

    @Override
    public BigDecimal checkBalance(accountNumberRequest request) {
        Account acc = accountRepository.findByAccountNumber(request.getAccountNumber());
        if(acc != null) {
            return acc.getBalance();
        } else {
            throw new accountNotFound("No account found with the accountNumber : " + request.getAccountNumber());
        }
    }

    @Override
    @Transactional
    public void transferAmount(transferRequest transferRequest) {
        long senderAccountNumber = transferRequest.getSenderAccountNumber();
        long receiverAccountNumber = transferRequest.getReceiverAccountNumber();
        Account senderAccount = accountRepository.findByAccountNumber(senderAccountNumber);
        Account receiverAccount = accountRepository.findByAccountNumber(receiverAccountNumber);
        BigDecimal amount = transferRequest.getAmount();
        if(senderAccount == null) {
            throw new accountNotFound("Sender account not found with accountNumber " + senderAccountNumber);
        } else if(receiverAccount == null) {
            throw new accountNotFound("Receiver account not found with accountNumber " + receiverAccountNumber);
        } else {
            if(senderAccount.getStatus().equals("ACTIVE") && receiverAccount.getStatus().equals("ACTIVE")) {
                if (amount.compareTo(BigDecimal.ZERO) < 1) {
                    throw new unableToDepositWithdraw("Cant transfer negative funds or zero");
                } else {
                    if (senderAccount.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 1) {
                        throw new insufficientFunds("No sufficient funds to transfer money");
                    } else {
                        receiverAccount.setBalance(receiverAccount.getBalance().add(amount));
                        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
                        Transaction senderTransaction = new Transaction(senderAccountNumber, "TRANSFER_OUT", amount);
                        Transaction receiverTransaction = new Transaction(receiverAccountNumber, "TRANSFER_IN", amount);
                        accountRepository.save(senderAccount);
                        accountRepository.save(receiverAccount);
                        transactionRepository.save(senderTransaction);
                        transactionRepository.save(receiverTransaction);
                    }
                }
            } else {
                throw new unableToDepositWithdraw("Unable to transfer money as account is not active ");
            }
        }
    }

    @Override
    public List<Transaction> findByAccountNumber(accountNumberRequest request) {
        return transactionRepository.findByAccountNumber(request.getAccountNumber());
    }

    @Override
    public void changeStatus(changeStatusRequest request) {
        long accountNumber = request.getAccountNumber();
        Account acc = accountRepository.findByAccountNumber(accountNumber);
        if(acc != null) {
            acc.setStatus(request.getStatus());
            accountRepository.save(acc);
        } else {
            throw new accountNotFound("No account found with accountNumber : " + accountNumber);
        }
    }
}
