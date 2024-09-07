package com.example.user_mircroservice.infrastructure.adapters.output.persistence;

import com.example.user_mircroservice.domain.models.Role;
import com.example.user_mircroservice.domain.ports.output.IRolePersistencePort;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.entity.RoleEntity;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.exceptions.RoleNotFoundException;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.mapper.RoleMapper;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.repository.RoleRepository;
import com.example.user_mircroservice.infrastructure.configuration.Constants;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RolePersistenceAdapterMySql implements IRolePersistencePort {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Role findById(long id) {
        RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(Constants.ROLE_NOT_FOUND_BY_ID));
        return roleMapper.toRole(roleEntity);
    }

    @Override
    public Role findByName(String name) {
        RoleEntity roleEntity = roleRepository.findByName(name).orElseThrow(()-> new RoleNotFoundException(Constants.ROLE_NOT_FOUND_BY_NAME));
        return roleMapper.toRole(roleEntity);
    }
}
