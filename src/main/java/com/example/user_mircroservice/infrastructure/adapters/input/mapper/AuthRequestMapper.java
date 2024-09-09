package com.example.user_mircroservice.infrastructure.adapters.input.mapper;

import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.request.AuthRequest;

public interface AuthRequestMapper {
    Auth toAuth(AuthRequest authRequest);
}
