package com.example.banking.controller;

import com.example.banking.entity.User;
import org.springframework.web.bind.annotation.*;
import com.example.banking.service.userService;
import com.example.banking.dto.loginRequest;
import com.example.banking.dto.loginResponse;
import com.example.banking.dto.registerResponse;
import com.example.banking.dto.registerRequest;

@RestController
@RequestMapping("/userapi")
public class userController {

    private userService userService;

    public userController(userService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public loginResponse login(@RequestBody loginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public registerResponse registerUser(@RequestBody registerRequest request) {
        System.out.println(request.getPhone());
        return userService.registerUser(request);
    }

    @PostMapping("/phonenumber")
    public User findUserByPhoneNumber(@RequestBody String phoneNumber) {
        return userService.findByPhoneNumber(phoneNumber);
    }

    @PostMapping("/email")
    public User findUserByEmail(@RequestBody  String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/testuser")
    public String sayHello() {
        return "api is working fine!";
    }

}
