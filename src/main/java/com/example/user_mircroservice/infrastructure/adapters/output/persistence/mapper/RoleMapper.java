package com.example.user_mircroservice.infrastructure.adapters.output.persistence.mapper;

import com.example.user_mircroservice.domain.models.Role;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.entity.RoleEntity;

public interface RoleMapper {
    Role toRole(RoleEntity roleEntity);
}
