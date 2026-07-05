package com.example.banking.dto;

public class usernameRequest {

    String username;

    public usernameRequest() {
    }

    public usernameRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
