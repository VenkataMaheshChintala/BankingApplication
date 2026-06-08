package com.example.banking.service;

import com.example.banking.entity.User;
import com.example.banking.dto.loginRequest;
import com.example.banking.dto.loginResponse;
import com.example.banking.dto.userRegisterResponse;
import com.example.banking.dto.userRegisterRequest;

public interface userService {

    loginResponse login(loginRequest request);

    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);

    User findByUserName(String userName);

    User findByUserId(long userId);

    userRegisterResponse registerUser(userRegisterRequest request);
}
