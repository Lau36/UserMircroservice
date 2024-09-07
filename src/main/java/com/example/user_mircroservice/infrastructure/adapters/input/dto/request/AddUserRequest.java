package com.example.user_mircroservice.infrastructure.adapters.input.dto.request;

import com.example.user_mircroservice.infrastructure.configuration.Constants;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AddUserRequest {

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String name;

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String lastName;

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String identification;

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String phoneNumber;

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private LocalDate birthDate;

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String email;

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String password;
}
