package com.kacperKwiatkowski.tweetApp.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ValidatorUtilTest {

    private static final String DUMMY_EXCEPTION_MESSAGE = "DUMMY_EXCEPTION_MESSAGE";

    @InjectMocks
    private ValidatorUtil validatorUtil;


    @Test
    void shouldGatherExceptionMessages() {
        // given
        List<Optional<String>> existingExceptions = List.of(
                Optional.of(DUMMY_EXCEPTION_MESSAGE),
                Optional.of(DUMMY_EXCEPTION_MESSAGE)
        );

        List<Optional<String>> nonExistingExceptions = List.of(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        );

        List<Optional<String>> allExceptionMessages = Stream
                .concat(existingExceptions.stream(), nonExistingExceptions.stream())
                .toList();

        // when
        List<String> exceptionMessages = validatorUtil.gatherExceptionMessages(allExceptionMessages);

        // then
        assertEquals(existingExceptions.size(), exceptionMessages.size());
    }
}