package com.example.user_mircroservice.infrastructure.adapters.output.persistence;


import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.domain.ports.output.IAuthenticationManagerPort;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

@AllArgsConstructor
public class AuthenticationManagerImpl implements IAuthenticationManagerPort {

    private final AuthenticationManager authenticationManager;

    @Override
    public Boolean authenticate(Auth auth) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }
}
