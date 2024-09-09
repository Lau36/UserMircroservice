package com.example.user_mircroservice.infrastructure.adapters.input.mapper.implementations;

import com.example.user_mircroservice.domain.models.Token;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.response.AuthResponse;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.AuthResponseMapper;

public class AuthResponseMapperImpl implements AuthResponseMapper {

    public AuthResponse toAuthResponse(Token token) {
        if(token == null){
            return null;
        }
        return new AuthResponse(token.getToken());
    }
}
