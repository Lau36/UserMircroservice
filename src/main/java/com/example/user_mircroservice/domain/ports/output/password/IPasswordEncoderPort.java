package com.example.user_mircroservice.domain.ports.output.password;

import com.example.user_mircroservice.domain.models.Auth;

public interface IPasswordEncoderPort {
    Boolean verifyPassword(Auth auth);
}
