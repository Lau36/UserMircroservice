package com.example.user_mircroservice.domain.exceptions;

public class UserNotLegalException extends RuntimeException {
    public UserNotLegalException(String message) {
        super(message);
    }
}
