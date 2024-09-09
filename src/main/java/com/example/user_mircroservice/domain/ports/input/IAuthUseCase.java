package com.example.user_mircroservice.domain.ports.input;

import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.domain.models.Token;

public interface IAuthUseCase {
    Token login(Auth auth);
}
