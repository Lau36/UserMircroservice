package com.example.user_mircroservice.infrastructure.adapters.output.persistence.mapper;

import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.entity.UserEntity;

public interface UserMapper {
    User toUser(UserEntity userEntity);
    UserEntity toUserEntity(User user);
}
