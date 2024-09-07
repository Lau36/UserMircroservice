package com.example.user_mircroservice.infrastructure.adapters.output.persistence.mapper;

import com.example.user_mircroservice.domain.models.Role;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.entity.RoleEntity;

public class RoleMapperImplement implements RoleMapper {

    public Role toRole(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        return new Role(
                roleEntity.getId(),
                roleEntity.getName(),
                roleEntity.getDescription()
        );
    }
}
