package com.example.banking.repository;

import com.example.banking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface transactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountNumber(long accountNumber);

}
