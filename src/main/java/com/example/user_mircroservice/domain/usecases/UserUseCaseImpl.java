package com.example.user_mircroservice.domain.usecases;

import com.example.user_mircroservice.domain.models.Role;
import com.example.user_mircroservice.domain.models.User;
import com.example.user_mircroservice.domain.ports.input.IUserUseCase;
import com.example.user_mircroservice.domain.ports.output.IRolePersistencePort;
import com.example.user_mircroservice.domain.ports.output.IUserPersistencePort;
import com.example.user_mircroservice.domain.utils.Constants;
import com.example.user_mircroservice.domain.validations.UserValidations;

public class UserUseCaseImpl implements IUserUseCase {
    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final UserValidations userValidations;

    public UserUseCaseImpl(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, UserValidations userValidations) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.userValidations = userValidations;
    }

    @Override
    public User createUser(User user) {
        userValidations.userValidation(user, userPersistencePort);
        Role role = rolePersistencePort.findByName(Constants.AUX_BODEGA); //Aux_bodega role
        user.setRole(role);
        return userPersistencePort.createUser(user);
    }
}
