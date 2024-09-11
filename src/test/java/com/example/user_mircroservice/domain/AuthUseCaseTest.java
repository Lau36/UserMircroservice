package com.example.user_mircroservice.domain;

import com.example.user_mircroservice.domain.exceptions.InvalidCredentialsException;
import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.domain.models.Token;
import com.example.user_mircroservice.domain.ports.output.IAuthPersistencePort;
import com.example.user_mircroservice.domain.ports.output.IAuthenticationManagerPort;
import com.example.user_mircroservice.domain.usecases.AuthUseCaseImpl;
import com.example.user_mircroservice.domain.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AuthUseCaseTest {


    @Mock
    private IAuthenticationManagerPort authenticationManagerPort;

    @Mock
    private IAuthPersistencePort authPersistencePort;

    @InjectMocks
    private AuthUseCaseImpl authUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void invalidCredentialsThrowsBadCredentialslsExceptionTest() {
        Auth auth = new Auth("test@example.com", "wrong_password");
        when(authenticationManagerPort.authenticate(auth)).thenReturn(false);
        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, () -> {
            authUseCase.login(auth);
        });
        assertEquals(Constants.INVALID_CREDENTIALS, exception.getMessage());
        verify(authenticationManagerPort,times(1)).authenticate(auth);
        verifyNoMoreInteractions(authPersistencePort);
    }

    @Test
    void loginSuccesfulTest() {
        Auth auth = new Auth("testuser@example.com", "password123");
        Token token = new Token("token");
        when(authenticationManagerPort.authenticate(auth)).thenReturn(true);
        when(authPersistencePort.login(auth)).thenReturn(token);


        Token actualToken = authUseCase.login(auth);

        assertEquals(token, actualToken);
        verify(authenticationManagerPort ,times(1)).authenticate(auth);
        verify(authPersistencePort ,times(1)).login(auth);
    }

}
