package com.example.user_mircroservice.infrastructure.adapters.output.persistence;

import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.domain.models.Token;
import com.example.user_mircroservice.domain.ports.output.IAuthPersistencePort;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.entity.UserEntity;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.repository.UserRepository;
import com.example.user_mircroservice.infrastructure.configuration.jwtconfig.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
public class AuthPersistenceImpl implements IAuthPersistencePort {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public Token login(Auth auth) {
        UserEntity userEntity = userRepository.findByEmail(auth.getEmail()).orElseThrow();
        UserDetails user = new UserDetailsImpl(userEntity);
        return new Token(jwtService.getToken(user, userEntity.getId()));
    }
}
