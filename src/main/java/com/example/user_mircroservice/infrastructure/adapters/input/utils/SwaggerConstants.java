package com.example.user_mircroservice.infrastructure.adapters.input.utils;

public class SwaggerConstants {
    private SwaggerConstants() {
    }

    public static final String CREATE_USER = "Create user";
    public static final String DESCRIPTION_CREATE_USER_AUX_BODEGA = "Endpoint to create a user with the aux bodega role, the user making this request must have the Admin role.";
    public static final String DESCRIPTION_CREATE_USER_CLIENTE = "Endpoint to create a user with the cliente role, the user making this request must have the Cliente role.";
    public static final String MESSAGE_CREATED = "User created successfully";
    public static final String MESSAGE_BAD_REQUEST = "Invalid input";
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String AUTH = "Login for all application users";
    public static final String SUSSCCESFULY_LOGIN = "Successful login";

}
