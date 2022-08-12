package com.kacperKwiatkowski.tweetApp.exception.exceptions;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FailedTweetValidationException extends RuntimeException {

    private List<String> exceptionMessages;
}
