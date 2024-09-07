package com.example.user_mircroservice.infrastructure.adapters.input.mapper;

import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.response.AddUserResponse;

public class UserResponseMapperImpl implements UserResponseMapper {

    public AddUserResponse toAddUserResponse(String message, User user) {
        if(user == null && message == null) {
            return null;
        }
        return new AddUserResponse(message, user.getEmail());
    }
}
