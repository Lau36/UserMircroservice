package com.example.user_mircroservice.domain;

import com.example.user_mircroservice.domain.models.Role;
import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.domain.ports.output.IRolePersistencePort;
import com.example.user_mircroservice.domain.ports.output.IUserPersistencePort;
import com.example.user_mircroservice.domain.usecases.UserUseCaseImpl;
import com.example.user_mircroservice.domain.utils.Constants;
import com.example.user_mircroservice.domain.validations.UserValidations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Spy
    private UserValidations userValidations;

    @InjectMocks
    private UserUseCaseImpl userUseCaseImpl;

    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        role = new Role(1L, Constants.AUX_BODEGA, "Auxiliar de Bodega");
        user = new User(1L, "Test", "User", "1234567890", "+573054748905", LocalDate.of(2000, 1, 1), "testuser@example.com", "password123", null);
    }

    @Test
    void testCreateUserSuccessfully() {
        doNothing().when(userValidations).userValidation(user, userPersistencePort);

        when(rolePersistencePort.findByName(Constants.AUX_BODEGA)).thenReturn(role);

        when(userPersistencePort.createUser(user)).thenReturn(user);

        User createdUser = userUseCaseImpl.createUser(user);

        verify(userValidations, times(1)).userValidation(user, userPersistencePort);
        verify(rolePersistencePort, times(1)).findByName(Constants.AUX_BODEGA);
        verify(userPersistencePort, times(1)).createUser(user);

        assertNotNull(createdUser);
        assertEquals(role, createdUser.getRole());
    }

    @Test
    void testCreateUserValidationFails() {
        doThrow(new RuntimeException("Validation failed")).when(userValidations).userValidation(user, userPersistencePort);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userUseCaseImpl.createUser(user));

        verify(userValidations, times(1)).userValidation(user, userPersistencePort);

        verifyNoInteractions(rolePersistencePort);
        verify(userPersistencePort, never()).createUser(any(User.class));

        assertEquals("Validation failed", exception.getMessage());
    }
}
