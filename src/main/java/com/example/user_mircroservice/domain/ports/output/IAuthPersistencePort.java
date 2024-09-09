package com.example.user_mircroservice.domain.ports.output;

import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.domain.models.Token;

public interface IAuthPersistencePort {
    Token login(Auth auth);
}
