package com.example.user_mircroservice.domain.models;

import java.util.Date;

public class User {
    private String name;
    private String lastName;
    private String identification;
    private String phoneNumber;
    private Date birthDate;
    private String email;
    private String password;

    public User(String name, String lastName, String identification, String phoneNumber, Date birthDate, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.identification = identification;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdentification() {
        return identification;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
