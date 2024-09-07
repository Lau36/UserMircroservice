package com.example.user_mircroservice.domain.validations;

import com.example.user_mircroservice.domain.exceptions.*;
import com.example.user_mircroservice.domain.ports.output.IUserPersistencePort;
import com.example.user_mircroservice.domain.utils.Constants;
import com.example.user_mircroservice.domain.models.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class UserValidations {
    public void userValidation (User user, IUserPersistencePort userPersistencePort) {

        if(user.getPhoneNumber().length()>13){
            throw new ExceededMaximumPhoneCharactersException(Fields.NUMERO_TELEFONICO.toString());
        }
        if (!isValidEmail(user.getEmail())) {
            throw new InvalidFielException(Fields.EMAIL.toString());
        }
        if (!isValidPhoneNumber(user.getPhoneNumber())) {
            throw new InvalidFielException(Fields.NUMERO_TELEFONICO.toString());
        }
        if (!isNumeric(user.getIdentification())) {
            throw new OnlyNumbersException(Fields.IDENTIFICACION.toString());
        }
        if (!isAdult(user.getBirthDate())) {
            throw new UserNotLegalException(Constants.USER_LEGAL_MESSAGE);
        }
        if(userPersistencePort.findUserByEmail(user.getEmail()).isPresent()) {
            throw new AlreadyExists(Fields.EMAIL.toString());
        }
        if(userPersistencePort.findUserByIdentification(user.getIdentification()).isPresent()) {
            throw new AlreadyExists(Fields.IDENTIFICACION.toString());
        }


    }

    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = Constants.EMAIL_REGULAR_EXPRESSION;
        return Pattern.matches(emailRegex, email);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        String phoneRegex = Constants.PHONE_REGULAR_EXPRESSION;
        return Pattern.matches(phoneRegex, phoneNumber);
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("\\d+");
    }

    private boolean isAdult(LocalDate birthDate) {
        if (birthDate == null) {
            return false;
        }
        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }


}
