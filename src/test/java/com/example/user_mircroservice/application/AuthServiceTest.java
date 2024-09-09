package com.example.user_mircroservice.application;

import com.example.user_mircroservice.application.services.AuthService;
import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.domain.models.Token;
import com.example.user_mircroservice.domain.ports.input.IAuthUseCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class AuthServiceTest {
    @Mock
    IAuthUseCase autUseCase;

    @InjectMocks
    AuthService authService;

    private Auth auth;
    private Token token;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        auth = new Auth("test@example.com", "password123");
        token = new Token("Token");


    }

    @Test
    void loginTest(){
        when(authService.login(auth)).thenReturn(token);

        Token result = authService.login(auth);
        assertEquals(token, result);
        verify(autUseCase, times(1)).login(auth);
    }
}
