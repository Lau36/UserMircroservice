package com.example.user_mircroservice.domain.utils;

public class Constants {
    private Constants() {
        throw new UnsupportedOperationException("This is a utility class");
    }
    public static final String USER_LEGAL_MESSAGE = "El usuario debe ser mayor de edad";
    public static final String EMAIL_REGULAR_EXPRESSION = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static final String PHONE_REGULAR_EXPRESSION = "^\\+?[0-9]{1,13}$";
    public static final String AUX_BODEGA = "Aux_bodega";
}
