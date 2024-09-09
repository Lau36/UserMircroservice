package com.example.user_mircroservice.domain;

import com.example.user_mircroservice.domain.exceptions.InvalidPasswordException;
import com.example.user_mircroservice.domain.exceptions.UserNotFoundException;
import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.domain.models.Token;
import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.domain.ports.output.IAuthPersistencePort;
import com.example.user_mircroservice.domain.ports.output.IUserPersistencePort;
import com.example.user_mircroservice.domain.ports.output.password.IPasswordEncoderPort;
import com.example.user_mircroservice.domain.usecases.AuthUseCaseImpl;
import com.example.user_mircroservice.domain.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AuthUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IPasswordEncoderPort passwordEncoderPort;

    @Mock
    private IAuthPersistencePort authPersistencePort;

    @InjectMocks
    private AuthUseCaseImpl authUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void userNotFoundThrowsUserNotFoundExceptionTest() {
        Auth auth = new Auth("test@example.com", "password");
        when(userPersistencePort.findUserByEmail(auth.getEmail())).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            authUseCase.login(auth);
        });
        assertEquals(Constants.USER_NOT_FOUND_BY_EMAIL, exception.getMessage());
        verify(userPersistencePort).findUserByEmail(auth.getEmail());
        verifyNoMoreInteractions(passwordEncoderPort, authPersistencePort);
    }

    @Test
    void invalidPasswordThrowsInvalidPasswordExceptionTest() {
        Auth auth = new Auth("test@example.com", "wrong_password");
        User user = new User(1L,
                "Test",
                "User",
                "1234567890",
                "+573054748905",
                LocalDate.of(2000, 1, 1),
                "testuser@example.com",
                "password123",
                null);
        when(userPersistencePort.findUserByEmail(auth.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoderPort.verifyPassword(auth)).thenReturn(false);

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> {
            authUseCase.login(auth);
        });
        assertEquals(Constants.INVALID_PASSWORD, exception.getMessage());
        verify(userPersistencePort).findUserByEmail(auth.getEmail());
        verify(passwordEncoderPort).verifyPassword(auth);
        verifyNoMoreInteractions(authPersistencePort);
    }

    @Test
    void loginSuccesfulTest() {
        Auth auth = new Auth("testuser@example.com", "password123");
        User user = new User(1L,
                "Test",
                "User",
                "1234567890",
                "+573054748905",
                LocalDate.of(2000, 1, 1),
                "testuser@example.com",
                "password123",
                null);
        Token token = new Token("token");
        when(userPersistencePort.findUserByEmail(auth.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoderPort.verifyPassword(auth)).thenReturn(true);
        when(authPersistencePort.login(auth)).thenReturn(token);

        Token actualToken = authUseCase.login(auth);

        assertEquals(token, actualToken);
        verify(userPersistencePort, times(1)).findUserByEmail(auth.getEmail());
        verify(passwordEncoderPort ,times(1)).verifyPassword(auth);
        verify(authPersistencePort ,times(1)).login(auth);
    }

}
