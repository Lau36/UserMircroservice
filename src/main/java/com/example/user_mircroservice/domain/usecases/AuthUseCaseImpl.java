package com.example.user_mircroservice.domain.usecases;

import com.example.user_mircroservice.domain.exceptions.InvalidCredentialsException;
import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.domain.models.Token;
import com.example.user_mircroservice.domain.ports.input.IAuthUseCase;
import com.example.user_mircroservice.domain.ports.output.IAuthPersistencePort;
import com.example.user_mircroservice.domain.ports.output.IAuthenticationManagerPort;
import com.example.user_mircroservice.domain.utils.Constants;

public class AuthUseCaseImpl implements IAuthUseCase {
    private final IAuthPersistencePort authPersistencePort;
    private final IAuthenticationManagerPort authenticationManagerPort;

    public AuthUseCaseImpl(IAuthPersistencePort authPersistencePort, IAuthenticationManagerPort authenticationManagerPort) {
        this.authenticationManagerPort = authenticationManagerPort;
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public Token login(Auth auth) {
        if(Boolean.FALSE.equals(authenticationManagerPort.authenticate(auth))) {
            throw new InvalidCredentialsException(Constants.INVALID_CREDENTIALS);
        }
        return authPersistencePort.login(auth);
    }
}
