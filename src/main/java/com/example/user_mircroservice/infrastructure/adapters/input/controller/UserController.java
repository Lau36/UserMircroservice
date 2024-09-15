package com.example.user_mircroservice.infrastructure.adapters.input.controller;

import com.example.user_mircroservice.application.services.UserService;
import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.request.AddUserRequest;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.response.AddUserResponse;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.UserRequestMapper;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.UserResponseMapper;
import com.example.user_mircroservice.infrastructure.configuration.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    @PostMapping
    public ResponseEntity<AddUserResponse> createUser(@RequestBody AddUserRequest addUserRequest) {
        User user = userRequestMapper.addRequestToUser(addUserRequest);
        User userSaved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseMapper.toAddUserResponse(Constants.USER_CREATED_SUCCESFULL, userSaved));
    }
    @PostMapping("/Customer")
    public ResponseEntity<AddUserResponse> createCustomerUser(@RequestBody AddUserRequest addUserRequest) {
        User user = userRequestMapper.addRequestToUser(addUserRequest);
        User userSaved = userService.createCustomerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseMapper.toAddUserResponse(Constants.USER_CREATED_SUCCESFULL, userSaved));
    }
}
