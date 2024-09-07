package com.example.user_mircroservice.infrastructure.adapters.output.persistence.repository;

import com.example.user_mircroservice.infrastructure.adapters.output.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findById(Long aLong);
    Optional<RoleEntity> findByName(String name);
}
