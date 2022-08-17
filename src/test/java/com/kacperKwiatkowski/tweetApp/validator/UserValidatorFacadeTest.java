package com.kacperKwiatkowski.tweetApp.validator;

import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedUserValidationException;
import com.kacperKwiatkowski.tweetApp.util.UserObjectProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserValidatorFacadeTest {

    private static final String DUMMY_EXCEPTION_MESSAGE = "DUMMY_EXCEPTION_MESSAGE";

    @Mock
    private UserValidator userValidator;

    @Mock
    private ValidatorUtil validatorUtil;

    @InjectMocks
    private UserValidatorFacade userValidatorFacade;

    @Test
    void shouldThrowFailedUserValidationException() {
        // given
        List<String> existingExceptions = List.of(DUMMY_EXCEPTION_MESSAGE);

        when(validatorUtil.gatherExceptionMessages(any())).thenReturn(existingExceptions);


        // when
        FailedUserValidationException exception = assertThrows(FailedUserValidationException.class, () -> userValidatorFacade.validateUserRegisterAction(UserObjectProvider.provideRegisterUserDto()));

        // then
        assertNotNull(exception);
        assertTrue(exception.getExceptionMessages().contains(DUMMY_EXCEPTION_MESSAGE));
    }

    @Test
    void shouldNotThrowFailedUserValidationException() {
        // given
        List<String> existingExceptions = List.of();

        when(validatorUtil.gatherExceptionMessages(any())).thenReturn(existingExceptions);


        // when & then
        assertDoesNotThrow(() -> userValidatorFacade.validateUserRegisterAction(UserObjectProvider.provideRegisterUserDto()));
    }
}