package com.example.user_mircroservice.infrastructure.adapters.input.mapper;

import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.response.AddUserResponse;

public interface UserResponseMapper {
    AddUserResponse toAddUserResponse(String message, User user);
}
