package com.example.user_mircroservice.infrastructure.adapters.input.controller;

import com.example.user_mircroservice.application.services.AuthService;
import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.request.AuthRequest;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.response.AuthResponse;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.AuthRequestMapper;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.AuthResponseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthRequestMapper authRequestMapper;
    private final AuthResponseMapper authResponseMapper;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        Auth auth = authRequestMapper.toAuth(authRequest);
        AuthResponse response = authResponseMapper.toAuthResponse(authService.login(auth));
        return ResponseEntity.ok(response);
    }
}
