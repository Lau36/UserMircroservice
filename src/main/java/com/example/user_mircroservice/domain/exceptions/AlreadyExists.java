package com.example.user_mircroservice.domain.exceptions;

public class AlreadyExists extends RuntimeException {
    public AlreadyExists(String message) {
        super(message);
    }
}
