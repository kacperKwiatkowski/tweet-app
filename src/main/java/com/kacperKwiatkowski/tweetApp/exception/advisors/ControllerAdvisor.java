package com.kacperKwiatkowski.tweetApp.exception.advisors;

import com.kacperKwiatkowski.tweetApp.exception.exceptions.AvatarParseException;
import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedTweetValidationException;
import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedUserValidationException;
import com.kacperKwiatkowski.tweetApp.validator.common.ValidationReport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String VALUES_VALIDATION_FAILURE_MESSAGE = "Values validation failed";
    private static final String TWEET_VALIDATION_FAILURE_MESSAGE = "Tweet validation failed";
    private static final String USER_VALIDATION_FAILURE_MESSAGE = "User validation failed";
    private static final String AVATAR_PARSE_FAILURE_MESSAGE = "Avatar image parse unsuccessful";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationReport> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + ": " + errorMessage);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ValidationReport(VALUES_VALIDATION_FAILURE_MESSAGE, errors));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ValidationReport> bindExceptionHandler(BindException e) {

        List<String> exceptionMessages = e.getFieldErrors().stream()
                .map(error -> "Incorrect value for field: " + error.getField())
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ValidationReport(TWEET_VALIDATION_FAILURE_MESSAGE, exceptionMessages));
    }

    @ExceptionHandler(FailedTweetValidationException.class)
    public ResponseEntity<ValidationReport> failedTweetValidationExceptionExceptionHandler(FailedTweetValidationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ValidationReport(TWEET_VALIDATION_FAILURE_MESSAGE, e.getExceptionMessages()));
    }

    @ExceptionHandler(FailedUserValidationException.class)
    public ResponseEntity<ValidationReport> failedUserValidationExceptionExceptionHandler(FailedUserValidationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ValidationReport(USER_VALIDATION_FAILURE_MESSAGE, e.getExceptionMessages()));
    }

    @ExceptionHandler(AvatarParseException.class)
    public ResponseEntity<String> failedUserValidationExceptionExceptionHandler(AvatarParseException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(AVATAR_PARSE_FAILURE_MESSAGE);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> usernameNotFoundExceptionHandler(UsernameNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(e.getMessage());
    }
}
