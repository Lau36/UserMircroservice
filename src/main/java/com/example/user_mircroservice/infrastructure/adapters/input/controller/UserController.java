package com.example.user_mircroservice.infrastructure.adapters.input.controller;

import com.example.user_mircroservice.application.services.UserService;
import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.request.AddUserRequest;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.response.AddUserResponse;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.UserRequestMapper;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.UserResponseMapper;
import com.example.user_mircroservice.infrastructure.adapters.input.utils.SwaggerConstants;
import com.example.user_mircroservice.infrastructure.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    @Operation(summary = SwaggerConstants.CREATE_USER,
            description = SwaggerConstants.DESCRIPTION_CREATE_USER_AUX_BODEGA,
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = SwaggerConstants.MESSAGE_CREATED,
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddUserResponse.class)) }),
            @ApiResponse(responseCode = "400", description = SwaggerConstants.MESSAGE_BAD_REQUEST,
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<AddUserResponse> createUser(@RequestBody AddUserRequest addUserRequest) {
        User user = userRequestMapper.addRequestToUser(addUserRequest);
        User userSaved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseMapper.toAddUserResponse(Constants.USER_CREATED_SUCCESFULL, userSaved));
    }

    @Operation(summary = SwaggerConstants.CREATE_USER,
            description = SwaggerConstants.DESCRIPTION_CREATE_USER_CLIENTE,
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = SwaggerConstants.MESSAGE_CREATED,
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddUserResponse.class)) }),
            @ApiResponse(responseCode = "400", description = SwaggerConstants.MESSAGE_BAD_REQUEST,
                    content = @Content)
    })
    @PostMapping("/Customer")
    public ResponseEntity<AddUserResponse> createCustomerUser(@RequestBody AddUserRequest addUserRequest) {
        User user = userRequestMapper.addRequestToUser(addUserRequest);
        User userSaved = userService.createCustomerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseMapper.toAddUserResponse(Constants.USER_CREATED_SUCCESFULL, userSaved));
    }
}
