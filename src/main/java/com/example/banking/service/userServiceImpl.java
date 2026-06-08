package com.example.banking.service;

import com.example.banking.dto.loginRequest;
import com.example.banking.entity.User;
import com.example.banking.repository.userRepository;
import com.example.banking.dto.loginResponse;
import org.springframework.stereotype.Service;
import com.example.banking.dto.registerResponse;
import com.example.banking.dto.registerRequest;

@Service
public class userServiceImpl implements userService{

    private userRepository userRepository;

    public userServiceImpl(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public loginResponse login(loginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        loginResponse response = new loginResponse();
        if(user == null) {
            response.setSuccess(false);
            response.setMessage("No user exists with username : " + request.getUsername());
        } else {
            if(user.getPassword().equals(request.getPassword())) {
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
    public registerResponse registerUser(registerRequest request) {
        registerResponse response = new registerResponse();
        System.out.println(request.getPhone());
        User user = new User(request.getUsername(),request.getPassword(),request.getPhone(),request.getEmail());
        System.out.println(user.getPhoneNumber());
        if((findByEmail(request.getEmail()) == null) && (findByPhoneNumber(request.getPhone()) == null)) {
            response.setStatus(true);
            response.setMessage("Registration Successful");
            System.out.println(user.getPhoneNumber());
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
