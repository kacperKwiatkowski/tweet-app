package com.kacperKwiatkowski.tweetApp.validator.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ValidationReport {

    public String validationFailureMessage;
    public List<String> validationFailureDetails;
}
