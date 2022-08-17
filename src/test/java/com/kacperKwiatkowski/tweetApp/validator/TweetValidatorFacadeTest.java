package com.kacperKwiatkowski.tweetApp.validator;

import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedTweetValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TweetValidatorFacadeTest {

    private static final String DUMMY_EXCEPTION_MESSAGE = "DUMMY_EXCEPTION_MESSAGE";
    private static final String DUMMY_USERNAME = "DUMMY_USERNAME";
    private static final UUID DUMMY_TWEET_ID = UUID.randomUUID();

    @Mock
    private UserValidator userValidator;

    @Mock
    private TweetValidator tweetValidator;

    @Mock
    private ValidatorUtil validatorUtil;

    @InjectMocks
    private TweetValidatorFacade tweetValidatorFacade;

    @Test
    void shouldThrowFailedTweetValidationExceptionForTweetSaveAction() {
        // given
        List<String> existingExceptions = List.of(DUMMY_EXCEPTION_MESSAGE);
        when(validatorUtil.gatherExceptionMessages(any())).thenReturn(existingExceptions);

        // when
        FailedTweetValidationException exception = assertThrows(FailedTweetValidationException.class, () -> tweetValidatorFacade.validateTweetSaveAction(DUMMY_USERNAME));

        // then
        assertNotNull(exception);
        assertTrue(exception.getExceptionMessages().contains(DUMMY_EXCEPTION_MESSAGE));
    }

    @Test
    void shouldNotThrowFailedTweetValidationExceptionForTweetSaveAction() {
        // given
        List<String> existingExceptions = List.of();
        when(validatorUtil.gatherExceptionMessages(any())).thenReturn(existingExceptions);

        // when & then
        assertDoesNotThrow(() -> tweetValidatorFacade.validateTweetSaveAction(DUMMY_USERNAME));
    }

    @Test
    void shouldThrowFailedTweetValidationExceptionForTweetUpdateAction() {
        // given
        List<String> existingExceptions = List.of(DUMMY_EXCEPTION_MESSAGE);
        when(validatorUtil.gatherExceptionMessages(any())).thenReturn(existingExceptions);

        // when
        FailedTweetValidationException exception = assertThrows(FailedTweetValidationException.class, () -> tweetValidatorFacade.validateTweetUpdateAction(DUMMY_USERNAME, DUMMY_TWEET_ID));

        // then
        assertNotNull(exception);
        assertTrue(exception.getExceptionMessages().contains(DUMMY_EXCEPTION_MESSAGE));
    }

    @Test
    void shouldNotThrowFailedTweetValidationExceptionForTweetUpdateAction() {
        // given
        List<String> existingExceptions = List.of();
        when(validatorUtil.gatherExceptionMessages(any())).thenReturn(existingExceptions);

        // when & then
        assertDoesNotThrow(() -> tweetValidatorFacade.validateTweetUpdateAction(DUMMY_USERNAME, DUMMY_TWEET_ID));
    }

    @Test
    void shouldThrowFailedTweetValidationExceptionForTweetLikeAction() {
        // given
        List<String> existingExceptions = List.of(DUMMY_EXCEPTION_MESSAGE);
        when(validatorUtil.gatherExceptionMessages(any())).thenReturn(existingExceptions);

        // when
        FailedTweetValidationException exception = assertThrows(FailedTweetValidationException.class, () -> tweetValidatorFacade.validateTweetLikeAction(DUMMY_USERNAME, DUMMY_TWEET_ID));

        // then
        assertNotNull(exception);
        assertTrue(exception.getExceptionMessages().contains(DUMMY_EXCEPTION_MESSAGE));
    }

    @Test
    void shouldNotThrowFailedTweetValidationExceptionForTweetLikeAction() {
        // given
        List<String> existingExceptions = List.of();
        when(validatorUtil.gatherExceptionMessages(any())).thenReturn(existingExceptions);

        // when & then
        assertDoesNotThrow(() -> tweetValidatorFacade.validateTweetLikeAction(DUMMY_USERNAME, DUMMY_TWEET_ID));
    }

    @Test
    void shouldThrowFailedTweetValidationExceptionForTweetReplyAction() {
        // given
        List<String> existingExceptions = List.of(DUMMY_EXCEPTION_MESSAGE);
        when(validatorUtil.gatherExceptionMessages(any())).thenReturn(existingExceptions);

        // when
        FailedTweetValidationException exception = assertThrows(FailedTweetValidationException.class, () -> tweetValidatorFacade.validateTweetReplyAction(DUMMY_USERNAME, DUMMY_TWEET_ID, DUMMY_TWEET_ID));

        // then
        assertNotNull(exception);
        assertTrue(exception.getExceptionMessages().contains(DUMMY_EXCEPTION_MESSAGE));
    }

    @Test
    void shouldNotThrowFailedTweetValidationExceptionForTweetReplyAction() {
        // given
        List<String> existingExceptions = List.of();
        when(validatorUtil.gatherExceptionMessages(any())).thenReturn(existingExceptions);

        // when & then
        assertDoesNotThrow(() -> tweetValidatorFacade.validateTweetReplyAction(DUMMY_USERNAME, DUMMY_TWEET_ID, DUMMY_TWEET_ID));
    }


    @Test
    void shouldThrowFailedTweetValidationExceptionForTweetDeleteAction() {
        // given
        List<String> existingExceptions = List.of(DUMMY_EXCEPTION_MESSAGE);
        when(validatorUtil.gatherExceptionMessages(any())).thenReturn(existingExceptions);

        // when
        FailedTweetValidationException exception = assertThrows(FailedTweetValidationException.class, () -> tweetValidatorFacade.validateTweetDeleteAction(DUMMY_USERNAME, DUMMY_TWEET_ID));

        // then
        assertNotNull(exception);
        assertTrue(exception.getExceptionMessages().contains(DUMMY_EXCEPTION_MESSAGE));
    }

    @Test
    void shouldNotThrowFailedTweetValidationExceptionForTweetDeleteAction() {
        // given
        List<String> existingExceptions = List.of();
        when(validatorUtil.gatherExceptionMessages(any())).thenReturn(existingExceptions);

        // when & then
        assertDoesNotThrow(() -> tweetValidatorFacade.validateTweetDeleteAction(DUMMY_USERNAME, DUMMY_TWEET_ID));
    }
}