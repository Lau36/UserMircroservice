package com.example.user_mircroservice.infrastructure.adapters.output.persistence;

import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.domain.ports.output.password.IPasswordEncoderPort;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.entity.UserEntity;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class PasswordEncoderImpl implements IPasswordEncoderPort {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public Boolean verifyPassword(Auth auth) {
        UserEntity user = userRepository.findByEmail(auth.getEmail()).orElseThrow();
        return passwordEncoder.matches(auth.getPassword(), user.getPassword());
    }
}
