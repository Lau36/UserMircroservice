package com.example.user_mircroservice.infrastructure.adapters.input.mapper;

import com.example.user_mircroservice.domain.models.Role;
import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.request.AddUserRequest;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class UserRequestMapperImpl implements UserRequestMapper{

    public User addRequestToUser(AddUserRequest addUserRequest) {
        if ( addUserRequest == null ) {
            return null;
        }

        Long id = null;
        String name = addUserRequest.getName();
        String lastName = addUserRequest.getLastName();
        String identification = addUserRequest.getIdentification();
        String phoneNumber = addUserRequest.getPhoneNumber();
        LocalDate birthDate = addUserRequest.getBirthDate();
        String email = addUserRequest.getEmail();
        String password = addUserRequest.getPassword();
        Role role = null;

        return new User( id, name, lastName, identification, phoneNumber, birthDate, email, password, role );
    }
}
