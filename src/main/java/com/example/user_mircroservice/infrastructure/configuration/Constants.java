package com.example.user_mircroservice.infrastructure.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String TOKEN_KEY = "JWT_SECRET";
    public static final String TOKEN_START_WITH = "Bearer ";
    public static final String LOGIN_SUCCESSFUL = "Inicio de sesión exitoso";
    public static final String USER_NOT_FOUND_BY_EMAIL = "El email del usuario no existe";
    public static final String ROLE_NOT_FOUND_BY_ID = "El id del rol no existe";
    public static final String ROLE_NOT_FOUND_BY_NAME = "El nombre del rol no existe";
    public static final String INVALID_FIELD = "El campo %s es invalido";
    public static final String EMPTY_FIELD = "El campo %s no puede estar vacio";
    public static final String ONLY_NUMBERS = "El campo %s debe contener solo números";
    public static final String USER_NOT_ADUTL ="El usuario debe de ser mayor de edad";
    public static final String USER_CREATED_SUCCESFULL = "El usuario se creó correctamente";
    public static final String EMPTY_FIELD_MESSAGE = "El campo no puede estar vacio";
    public static final String ALREADY_EXISTS = "ya existe un usuario con el valor del campo %s en la base de datos";
    public static final String EXCEEDED_MAXIMUM_CHARACTERS = "El campo %s excede el número maximo de caracteres";



}
