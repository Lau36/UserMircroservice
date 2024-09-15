package com.example.user_mircroservice.domain.ports.input;

import com.example.user_mircroservice.domain.models.User;

public interface IUserUseCase {
    User createUser(User user);
    User createCustomerUser(User user);
}
