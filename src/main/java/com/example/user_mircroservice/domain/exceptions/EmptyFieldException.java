package com.example.user_mircroservice.domain.exceptions;

public class EmptyFieldException extends RuntimeException{
    public EmptyFieldException(String message) {
        super(message);
    }
}
