package com.example.user_mircroservice.infrastructure.adapters.input.controller;

import com.example.user_mircroservice.application.services.AuthService;
import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.request.AuthRequest;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.response.AddUserResponse;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.response.AuthResponse;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.AuthRequestMapper;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.AuthResponseMapper;
import com.example.user_mircroservice.infrastructure.adapters.input.utils.SwaggerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = SwaggerConstants.AUTH)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerConstants.SUSSCCESFULY_LOGIN,
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddUserResponse.class)) }),
            @ApiResponse(responseCode = "400", description = SwaggerConstants.INVALID_CREDENTIALS,
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        Auth auth = authRequestMapper.toAuth(authRequest);
        AuthResponse response = authResponseMapper.toAuthResponse(authService.login(auth));
        return ResponseEntity.ok(response);
    }
}
