package com.example.banking.dto;

public class loginResponse {

    boolean success;
    String message;
    String token;
    long userId;

    public loginResponse() {
    }

    public loginResponse(boolean success, String message,String token,long userId) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.userId = userId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
