package com.example.user_mircroservice.infrastructure.adapters.output.persistence;

import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.domain.ports.output.IUserPersistencePort;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.entity.UserEntity;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.mapper.UserMapper;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@AllArgsConstructor
public class UserPersistenceAdapterMySql implements IUserPersistencePort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        UserEntity userEntity = userMapper.toUserEntity(user);
        UserEntity userSaved = userRepository.save(userEntity);
        return userMapper.toUser(userSaved);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.map(userMapper::toUser);
    }

    @Override
    public Optional<User> findUserByIdentification(String identification) {
        Optional<UserEntity> userEntity = userRepository.findByIdentification(identification);
        return userEntity.map(userMapper::toUser);
    }
}
