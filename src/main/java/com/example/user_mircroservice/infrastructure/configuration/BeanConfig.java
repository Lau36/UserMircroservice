package com.example.user_mircroservice.infrastructure.configuration;

import com.example.user_mircroservice.application.services.UserService;
import com.example.user_mircroservice.domain.ports.output.IRolePersistencePort;
import com.example.user_mircroservice.domain.ports.output.IUserPersistencePort;
import com.example.user_mircroservice.domain.usecases.UserUseCaseImpl;
import com.example.user_mircroservice.domain.validations.UserValidations;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.UserRequestMapperImpl;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.UserResponseMapperImpl;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.RolePersistenceAdapterMySql;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.UserPersistenceAdapterMySql;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.mapper.*;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.repository.RoleRepository;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {
    @Bean
    public UserService userService(final IUserPersistencePort userPersistencePort, final IRolePersistencePort rolePersistencePort, final UserValidations userValidations) {
        return new UserService(new UserUseCaseImpl(userPersistencePort, rolePersistencePort, userValidations));
    }

    @Bean
    public IUserPersistencePort userPersistencePort(final UserRepository userRepository, final UserMapper userMapper, final PasswordEncoder passwordEncoder) {
        return new UserPersistenceAdapterMySql(userRepository, userMapper, passwordEncoder);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort(final RoleRepository roleRepository, final RoleMapper roleMapper) {
        return new RolePersistenceAdapterMySql(roleRepository, roleMapper);
    }

    @Bean
    public UserRequestMapperImpl userRequestMapper() {
        return new UserRequestMapperImpl();
    }

    @Bean
    public UserMapperImpl userMapper() {
        return new UserMapperImpl();
    }

    @Bean
    public RoleMapperImplement roleMapper() {
        return new RoleMapperImplement();
    }

    @Bean
    public UserValidations userValidations() {
        return new UserValidations();
    }

    @Bean
    public UserResponseMapperImpl userResponseMapper(){
        return new UserResponseMapperImpl();
    }
}
