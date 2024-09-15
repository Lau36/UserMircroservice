package com.example.user_mircroservice.application;

import com.example.user_mircroservice.application.services.UserService;
import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.domain.ports.input.IUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

 class UserServiceTest {
    @Mock
    IUserUseCase userUseCase;

    @InjectMocks
    UserService userService;

    private User user;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(1L, "Usertest","userlastname","1109668712","+573054748905",LocalDate.now(),"email@gmail.com","contraseña123",null);
    }

    @Test
    void createUserTest(){
        when(userService.createUser(user)).thenReturn(user);

        User userCreated = userService.createUser(user);

        assertEquals(user, userCreated);
        verify(userUseCase, times(1)).createUser(user);
    }
}
