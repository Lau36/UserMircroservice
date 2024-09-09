package com.example.user_mircroservice.infrastructure.configuration;

import com.example.user_mircroservice.application.services.AuthService;
import com.example.user_mircroservice.application.services.UserService;
import com.example.user_mircroservice.domain.ports.output.IAuthPersistencePort;
import com.example.user_mircroservice.domain.ports.output.IRolePersistencePort;
import com.example.user_mircroservice.domain.ports.output.IUserPersistencePort;
import com.example.user_mircroservice.domain.ports.output.password.IPasswordEncoderPort;
import com.example.user_mircroservice.domain.usecases.AuthUseCaseImpl;
import com.example.user_mircroservice.domain.usecases.UserUseCaseImpl;
import com.example.user_mircroservice.domain.validations.UserValidations;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.implementations.AuthRequestMapperImpl;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.implementations.AuthResponseMapperImpl;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.implementations.UserRequestMapperImpl;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.implementations.UserResponseMapperImpl;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.*;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.mapper.*;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.mapper.implementations.RoleMapperImplement;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.mapper.implementations.UserMapperImpl;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.repository.RoleRepository;
import com.example.user_mircroservice.infrastructure.adapters.output.persistence.repository.UserRepository;
import com.example.user_mircroservice.infrastructure.configuration.jwtconfig.JwtService;
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
    public AuthService authService(final IUserPersistencePort userPersistencePort, final IPasswordEncoderPort passwordEncoder, final IAuthPersistencePort authPersistencePort) {
        return new AuthService(new AuthUseCaseImpl(userPersistencePort, passwordEncoder, authPersistencePort));
    }

    @Bean
    public IAuthPersistencePort authPersistencePort(final UserRepository userRepository, final JwtService jwtService) {
        return new AuthPersistenceImpl(userRepository, jwtService);
    }

    @Bean
    public IPasswordEncoderPort passwordEncoderPort(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        return new PasswordEncoderImpl(passwordEncoder, userRepository);
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

    @Bean
    public AuthResponseMapperImpl authResponseMapperImpl2(){
        return new AuthResponseMapperImpl();
    }

    @Bean
    public AuthRequestMapperImpl authRequestMapper(){
        return new AuthRequestMapperImpl();
    }
}
