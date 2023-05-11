package com.example.bloodbank_project.response;

public class LoginResponse {

    private String token;
    private String role;
    private String message;
    private boolean status;
    int id;

    public LoginResponse(String token, String role, int id) {
        this.token = token;
        this.role = role;
        this.id = id;
    }

    public LoginResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public LoginResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }
}

