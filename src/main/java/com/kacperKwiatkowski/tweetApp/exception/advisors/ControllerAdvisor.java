package com.kacperKwiatkowski.tweetApp.exception.advisors;

import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedTweetValidationException;
import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedUserValidationException;
import com.kacperKwiatkowski.tweetApp.validator.ValidationReport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String TWEET_VALIDATION_FAILURE_MESSAGE = "Tweet validation failed";
    private static final String USER_VALIDATION_FAILURE_MESSAGE = "User validation failed";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
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
}
