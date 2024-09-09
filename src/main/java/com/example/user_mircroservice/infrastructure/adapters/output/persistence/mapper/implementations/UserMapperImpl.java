package com.example.user_mircroservice.infrastructure.adapters.output.persistence.mapper.implementations;

import com.example.user_mircroservice.domain.models.Role;
import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.entity.RoleEntity;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.entity.UserEntity;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.mapper.UserMapper;

public class UserMapperImpl implements UserMapper {

    public User toUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        Role role = null;
        if (userEntity.getRole() != null) {
            role = new Role(
                    userEntity.getRole().getId(),
                    userEntity.getRole().getName(),
                    userEntity.getRole().getDescription()
            );
        }

        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getLastName(),
                userEntity.getIdentification(),
                userEntity.getPhoneNumber(),
                userEntity.getBirthDate(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                role
        );
    }

    public UserEntity toUserEntity(User user) {
        if (user == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setLastName(user.getLastName());
        userEntity.setIdentification(user.getIdentification());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setBirthDate(user.getBirthDate());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        if (user.getRole() != null) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setId(user.getRole().getId());
            roleEntity.setName(user.getRole().getName());
            roleEntity.setDescription(user.getRole().getDescription());
            userEntity.setRole(roleEntity);
        }

        return userEntity;
    }
}
