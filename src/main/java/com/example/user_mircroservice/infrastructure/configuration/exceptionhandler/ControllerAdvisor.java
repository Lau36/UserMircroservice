package com.example.user_mircroservice.infrastructure.configuration.exceptionhandler;

import com.example.user_mircroservice.domain.exceptions.*;
import com.example.user_mircroservice.infrastructure.configuration.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(InvalidFielException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidFielException(InvalidFielException e) {
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                String.format(Constants.INVALID_FIELD, e.getMessage()),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()));
    }
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException e) {
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        String.format(Constants.EMPTY_FIELD, e.getMessage()),
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now()
                )
        );
    }
    @ExceptionHandler(OnlyNumbersException.class)
    public ResponseEntity<ExceptionResponse> handleOnlyNumbersException(OnlyNumbersException e) {
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        String.format(Constants.ONLY_NUMBERS, e.getMessage()),
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now()
                )
        );
    }
    @ExceptionHandler(UserNotLegalException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotLegalException() {
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        String.format(Constants.USER_NOT_ADUTL),
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now()
                )
        );
    }
    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<ExceptionResponse> handleAlreadyExists(AlreadyExists e) {
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        String.format(Constants.ALREADY_EXISTS, e.getMessage()),
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now()
                )
        );
    }
    @ExceptionHandler(ExceededMaximumPhoneCharactersException.class)
    public ResponseEntity<ExceptionResponse> handleExceededMaximumPhoneCharactersException(ExceededMaximumPhoneCharactersException e) {
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        String.format(Constants.EXCEEDED_MAXIMUM_CHARACTERS, e.getMessage()),
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now()
                )
        );
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        String.format(e.getMessage()),
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now()
                )
        );
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidPasswordException(InvalidCredentialsException e) {
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        String.format(e.getMessage()),
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now()
                )
        );
    }


}
