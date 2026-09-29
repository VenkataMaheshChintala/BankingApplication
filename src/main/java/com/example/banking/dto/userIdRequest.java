package com.example.banking.dto;

public class userIdRequest {

    long userId;

    public userIdRequest(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
