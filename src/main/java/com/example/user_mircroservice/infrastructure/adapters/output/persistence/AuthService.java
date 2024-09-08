package com.example.user_mircroservice.infrastructure.adapters.output.persistence;

import com.example.user_mircroservice.infrastructure.adapters.input.dto.request.AuthRequest;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.response.AuthResponse;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.entity.UserEntity;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.repository.UserRepository;
import com.example.user_mircroservice.infrastructure.configuration.jwtconfig.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest authRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        UserEntity userEntity = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        UserDetails user = new UserDetailsImpl(userEntity);
        String token = jwtService.getToken(user);
        return new AuthResponse(token, "Inicio de sesion exitoso");

    }
}
