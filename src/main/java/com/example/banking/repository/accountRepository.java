package com.example.banking.repository;

import com.example.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface accountRepository extends JpaRepository<Account ,Long> {

    int countByUserIdAndAccountType(long userId,String accountType);

    Account findByAccountNumber(long accountNumber);

    @Query("SELECT MAX(accountNumber) from Account")
    int getMaxAccountNumber();

    List<Account> findAllByuserId(long userId);

}
