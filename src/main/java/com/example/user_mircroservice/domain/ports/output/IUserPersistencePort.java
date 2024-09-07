package com.example.user_mircroservice.domain.ports.output;

import com.example.user_mircroservice.domain.models.User;

import java.util.Optional;

public interface IUserPersistencePort {
    User createUser(User user);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByIdentification(String identification);
}
