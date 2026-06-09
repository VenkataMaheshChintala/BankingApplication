package com.example.banking.service;

import com.example.banking.dto.loginRequest;
import com.example.banking.entity.User;
import com.example.banking.repository.userRepository;
import com.example.banking.dto.loginResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.banking.dto.userRegisterResponse;
import com.example.banking.dto.userRegisterRequest;

@Service
public class userServiceImpl implements userService{

    private userRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public userServiceImpl(userRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public loginResponse login(loginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        loginResponse response = new loginResponse();
        if(user == null) {
            response.setSuccess(false);
            response.setMessage("No user exists with username : " + request.getUsername());
        } else {
            if(passwordEncoder.matches(request.getPassword(),user.getPassword())) {
                response.setSuccess(true);
                response.setMessage("Login successful");
            } else {
                response.setSuccess(false);
                response.setMessage("Incorrect password for username : " + user.getUsername());
            }
        }
        return response;
    }

    @Override
    public User findByEmail(String email) {
        return  userRepository.findByEmail(email);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public userRegisterResponse registerUser(userRegisterRequest request) {
        userRegisterResponse response = new userRegisterResponse();
        if((findByEmail(request.getEmail()) == null) && (findByPhoneNumber(request.getPhone()) == null)) {
            User user = new User(request.getUsername(),"",request.getPhone(),request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            response.setStatus(true);
            response.setMessage("Registration Successful");
            userRepository.save(user);
        } else {
            response.setStatus(false);
            response.setMessage("Registration Failed");
        }
        return response;
    }

    @Override
    public User findByUserId(long userId) {
        return userRepository.findByUserId(userId);
    }

}
