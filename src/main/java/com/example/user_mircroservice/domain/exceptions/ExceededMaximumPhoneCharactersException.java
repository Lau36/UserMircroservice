package com.example.user_mircroservice.domain.exceptions;

public class ExceededMaximumPhoneCharactersException extends RuntimeException {
    public ExceededMaximumPhoneCharactersException(String message) {
        super(message);
    }
}
