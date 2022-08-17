package com.kacperKwiatkowski.tweetApp.validator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidatorUtil {

    public List<String> gatherExceptionMessages(List<Optional<String>> performedValidationMessages) {
        return performedValidationMessages
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
