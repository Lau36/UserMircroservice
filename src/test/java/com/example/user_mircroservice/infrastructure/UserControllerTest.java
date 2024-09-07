package com.example.user_mircroservice.infrastructure;

import com.example.user_mircroservice.application.services.UserService;
import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.infrastructure.adapters.input.controller.UserController;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.request.AddUserRequest;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.response.AddUserResponse;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.UserRequestMapper;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.UserResponseMapper;
import com.example.user_mircroservice.infrastructure.configuration.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

 class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRequestMapper userRequestMapper;

    @Mock
    private UserResponseMapper userResponseMapper;

    @InjectMocks
    private UserController userController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser(){
        // Arrange
        AddUserRequest addUserRequest = new AddUserRequest("John", "Doe", "123456789", "+573054748905", null, "john.doe@example.com", "password123");
        User user = new User(1L, "John", "Doe", "123456789", "+573054748905", null, "john.doe@example.com", "password123", null);
        AddUserResponse addUserResponse = new AddUserResponse(Constants.USER_CREATED_SUCCESFULL, user.getEmail());

        when(userRequestMapper.addRequestToUser(any(AddUserRequest.class))).thenReturn(user);
        when(userService.createUser(any(User.class))).thenReturn(user);
        when(userResponseMapper.toAddUserResponse(any(String.class), any(User.class))).thenReturn(addUserResponse);

        ResponseEntity<AddUserResponse> response = userController.createUser(addUserRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(addUserResponse, response.getBody());

        verify(userRequestMapper, times(1)).addRequestToUser(addUserRequest);
        verify(userService, times(1)).createUser(user);
        verify(userResponseMapper,times(1)).toAddUserResponse(Constants.USER_CREATED_SUCCESFULL,user);

    }
}
