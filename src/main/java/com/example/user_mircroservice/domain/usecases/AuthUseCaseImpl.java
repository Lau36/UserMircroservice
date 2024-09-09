package com.example.user_mircroservice.domain.usecases;

import com.example.user_mircroservice.domain.exceptions.InvalidPasswordException;
import com.example.user_mircroservice.domain.exceptions.UserNotFoundException;
import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.domain.models.Token;
import com.example.user_mircroservice.domain.ports.input.IAuthUseCase;
import com.example.user_mircroservice.domain.ports.output.IAuthPersistencePort;
import com.example.user_mircroservice.domain.ports.output.IUserPersistencePort;
import com.example.user_mircroservice.domain.ports.output.password.IPasswordEncoderPort;
import com.example.user_mircroservice.domain.utils.Constants;

public class AuthUseCaseImpl implements IAuthUseCase {
    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final IAuthPersistencePort authPersistencePort;

    public AuthUseCaseImpl(IUserPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoderPort, IAuthPersistencePort authPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public Token login(Auth auth) {
        if(userPersistencePort.findUserByEmail(auth.getEmail()).isEmpty()) {
            throw new UserNotFoundException(Constants.USER_NOT_FOUND_BY_EMAIL);
        }
        if(Boolean.FALSE.equals(passwordEncoderPort.verifyPassword(auth))){
            throw new InvalidPasswordException(Constants.INVALID_PASSWORD);
        }
        return authPersistencePort.login(auth);
    }
}
