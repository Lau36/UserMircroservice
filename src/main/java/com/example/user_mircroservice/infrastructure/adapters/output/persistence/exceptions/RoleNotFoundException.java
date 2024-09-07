package com.example.user_mircroservice.infrastructure.adapters.output.persistence.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
