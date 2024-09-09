package com.example.user_mircroservice.infrastructure;
import com.example.user_mircroservice.application.services.AuthService;
import com.example.user_mircroservice.domain.models.Auth;
import com.example.user_mircroservice.domain.models.Token;
import com.example.user_mircroservice.infrastructure.adapters.input.controller.AuthController;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.request.AuthRequest;
import com.example.user_mircroservice.infrastructure.adapters.input.dto.response.AuthResponse;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.AuthRequestMapper;
import com.example.user_mircroservice.infrastructure.adapters.input.mapper.AuthResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class AuthControllerTest {
    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @Mock
    private AuthRequestMapper authRequestMapper;

    @Mock
    private AuthResponseMapper authResponseMapper;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void loginTest() throws Exception {
        AuthRequest authRequest = new AuthRequest("testuser@example.com", "password123");
        Auth auth = new Auth(authRequest.getEmail(), authRequest.getPassword());
        Token token = new Token("token");
        AuthResponse authResponse = new AuthResponse("token");

        when(authRequestMapper.toAuth(any(AuthRequest.class))).thenReturn(auth);
        when(authService.login(any(Auth.class))).thenReturn(token);
        when(authResponseMapper.toAuthResponse(any(Token.class))).thenReturn(authResponse);

        mockMvc.perform(post("/Auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"testuser@example.com\", \"password\": \"password123\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").value("token"));
    }
}
