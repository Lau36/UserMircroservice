package com.example.user_mircroservice.domain.models;

public class Auth {
    private String email;
    private String password;

    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
