package com.kacperKwiatkowski.tweetApp.exception.advisors;

import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedTweetValidationException;
import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedUserValidationException;
import com.kacperKwiatkowski.tweetApp.validator.ValidationReport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String TWEET_VALIDATION_FAILURE_MESSAGE = "Tweet validation failed";
    private static final String USER_VALIDATION_FAILURE_MESSAGE = "User validation failed";

    @ExceptionHandler(FailedTweetValidationException.class)
    public ResponseEntity<ValidationReport> failedTweetValidationExceptionExceptionHandler(FailedTweetValidationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ValidationReport(TWEET_VALIDATION_FAILURE_MESSAGE, e.getExceptionMessages()));
    }

    @ExceptionHandler(FailedUserValidationException.class)
    public ResponseEntity<ValidationReport> failedTweetValidationExceptionExceptionHandler(FailedUserValidationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ValidationReport(USER_VALIDATION_FAILURE_MESSAGE, e.getExceptionMessages()));
    }
}
