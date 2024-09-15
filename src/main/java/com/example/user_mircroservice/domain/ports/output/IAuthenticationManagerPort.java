package com.example.user_mircroservice.domain.ports.output;

import com.example.user_mircroservice.domain.models.Auth;

public interface IAuthenticationManagerPort {
    Boolean authenticate(Auth auth);
}
