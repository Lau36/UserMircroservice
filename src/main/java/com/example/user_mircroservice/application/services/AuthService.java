package com.example.user_mircroservice.application.services;

import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.domain.models.Token;
import com.example.user_mircroservice.domain.ports.input.IAuthUseCase;

public class AuthService implements IAuthUseCase {
    private final IAuthUseCase authUseCase;

    public AuthService(IAuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @Override
    public Token login(Auth auth) {
        return authUseCase.login(auth);
    }
}
