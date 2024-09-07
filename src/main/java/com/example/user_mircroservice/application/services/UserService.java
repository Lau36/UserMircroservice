package com.example.user_mircroservice.application.services;

import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.domain.ports.input.IUserUseCase;

public class UserService implements IUserUseCase {
    private final IUserUseCase userUseCase;

    public UserService(IUserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @Override
    public User createUser(User user) {
        return userUseCase.createUser(user);
    }
}
