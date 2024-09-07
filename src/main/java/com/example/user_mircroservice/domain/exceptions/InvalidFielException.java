package com.example.user_mircroservice.domain.exceptions;

public class InvalidFielException extends RuntimeException {
    public InvalidFielException(String message) {
        super(message);
    }
}
