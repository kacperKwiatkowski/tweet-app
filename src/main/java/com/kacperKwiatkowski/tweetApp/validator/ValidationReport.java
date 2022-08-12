package com.kacperKwiatkowski.tweetApp.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ValidationReport {

    public String validationFailureMessage;
    public List<String> validationFailureDetails;
}
