package com.example.user_mircroservice.domain;

import com.example.user_mircroservice.domain.exceptions.*;
import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.domain.ports.output.IUserPersistencePort;
import com.example.user_mircroservice.domain.validations.UserValidations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserValidationsTest {
    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserValidations userValidations;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(1L, "Test", "User", "1234567890", "+573054748905", LocalDate.of(2000, 1, 1), "testuser@example.com", "password123", null);
    }

    @Test
    void testPhoneNumberTooLong() {
        User user5 = new User(1L,
                "Test",
                "User",
                "1109887654",
                "+5730547489053456",  // Número muy largo
                LocalDate.of(2000, 1, 1),
                "email.@gmail.com",
                "password123",
                null);

        assertThrows(ExceededMaximumPhoneCharactersException.class, () -> {
            userValidations.userValidation(user5, userPersistencePort);
        });
    }

    @Test
    void testCreateUserWithInvalidPhoneNumber() {
        User user3 = new User(1L,
                "Test",
                "User",
                "1234567890",
                "invalidphone",
                LocalDate.of(2000, 1, 1),
                "email@gmail.com",
                "password123",
                null);

        assertThrows(InvalidFielException.class, () -> {
            userValidations.userValidation(user3, userPersistencePort);
        });
    }

    @Test
    void testCreateUserWithInvalidEmail() {
        User user2 = new User(1L,
                "Test",
                "User",
                "1234567890",
                "+573054748905",
                LocalDate.of(2000, 1, 1),
                "invalidEmail",
                "password123",
                null);
        when(userPersistencePort.findUserByEmail(anyString())).thenReturn(Optional.empty());

        InvalidFielException exception = assertThrows(InvalidFielException.class, () -> {
            userValidations.userValidation(user2, userPersistencePort);
        });

        assertEquals(Fields.EMAIL.toString(), exception.getMessage());
    }

    @Test
    void testCreateUserWithInvalidEmailWithNull() {
        User user2 = new User(1L,
                "Test",
                "User",
                "1234567890",
                "+573054748905",
                LocalDate.of(2000, 1, 1),
                null,
                "password123",
                null);
        when(userPersistencePort.findUserByEmail(anyString())).thenReturn(Optional.empty());

        InvalidFielException exception = assertThrows(InvalidFielException.class, () -> {
            userValidations.userValidation(user2, userPersistencePort);
        });

        assertEquals(Fields.EMAIL.toString(), exception.getMessage());
    }



    @Test
    void testOnlyNumbersInIdentification() {
        User user3 = new User(1L,
                "Test",
                "User",
                "123aaa",
                "+573054748905",
                LocalDate.of(2000, 1, 1),
                "email.@gmail.com",
                "password123",
                null);

        assertThrows(OnlyNumbersException.class, () -> {
            userValidations.userValidation(user3, userPersistencePort);
        });
    }

    @Test
    void testUserNotLegal() {
        User user4 = new User(1L,
                "Test",
                "User",
                "1109887654",
                "+573054748905",
                LocalDate.of(2010, 1, 1),
                "email.@gmail.com",
                "password123",
                null);

        assertThrows(UserNotLegalException.class, () -> {
            userValidations.userValidation(user4, userPersistencePort);
        });
    }

    @Test
    void testEmailAlreadyExists() {
        when(userPersistencePort.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertThrows(AlreadyExists.class, () -> {
            userValidations.userValidation(user, userPersistencePort);
        });
    }

    @Test
    void testIdentificationAlreadyExists() {
        when(userPersistencePort.findUserByIdentification(user.getIdentification())).thenReturn(Optional.of(user));

        assertThrows(AlreadyExists.class, () -> {
            userValidations.userValidation(user, userPersistencePort);
        });
    }



}
