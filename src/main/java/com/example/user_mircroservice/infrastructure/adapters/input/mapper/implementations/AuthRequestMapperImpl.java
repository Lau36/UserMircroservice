package com.example.user_mircroservice.infrastructure.adapters.input.mapper.implementations;

import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.request.AuthRequest;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.AuthRequestMapper;

public class AuthRequestMapperImpl implements AuthRequestMapper {
    @Override
    public Auth toAuth(AuthRequest authRequest) {
        if(authRequest == null){
            return null;
        }
        return new Auth(authRequest.getEmail(), authRequest.getPassword());
    }
}
