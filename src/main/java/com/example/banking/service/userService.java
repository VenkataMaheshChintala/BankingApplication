package com.example.banking.service;

import com.example.banking.entity.Transaction;
import com.example.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.banking.dto.loginRequest;
import com.example.banking.dto.loginResponse;
import com.example.banking.dto.registerResponse;
import com.example.banking.dto.registerRequest;

import java.util.List;

public interface userService {

    loginResponse login(loginRequest request);

    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);

    User findByUserId(long userId);

    registerResponse registerUser(registerRequest request);
}
