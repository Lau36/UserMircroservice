package com.example.user_mircroservice.infrastructure.adapters.input.mapper;

import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.request.AddUserRequest;

public interface UserRequestMapper {
    User addRequestToUser(AddUserRequest addUserRequest);
}
