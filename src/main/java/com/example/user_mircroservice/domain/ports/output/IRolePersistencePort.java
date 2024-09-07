package com.example.user_mircroservice.domain.ports.output;

import com.example.user_mircroservice.domain.models.Role;


public interface IRolePersistencePort {
    Role findById(long id);
    Role findByName(String name);
}
