package com.example.user_mircroservice.infrastructure.adapters.input.mapper;

import com.example.user_mircroservice.domain.models.Token;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.response.AuthResponse;

public interface AuthResponseMapper {
    AuthResponse toAuthResponse(Token token);
}
