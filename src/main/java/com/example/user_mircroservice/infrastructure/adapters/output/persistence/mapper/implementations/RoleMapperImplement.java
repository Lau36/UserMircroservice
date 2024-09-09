package com.example.user_mircroservice.infrastructure.adapters.output.persistence.mapper.implementations;

import com.example.user_mircroservice.domain.models.Role;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.entity.RoleEntity;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.mapper.RoleMapper;

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
