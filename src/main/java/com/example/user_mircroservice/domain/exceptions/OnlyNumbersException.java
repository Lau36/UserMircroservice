package com.example.user_mircroservice.domain.exceptions;

public class OnlyNumbersException extends RuntimeException {
    public OnlyNumbersException(String message) {
        super(message);
    }
}
