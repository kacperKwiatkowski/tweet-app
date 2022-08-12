package com.kacperKwiatkowski.tweetApp.validator;

import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedTweetValidationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public record TweetValidatorFacade(
        UserValidator userValidator,
        TweetValidator tweetValidator
) {

    public void validateTweetSaveAction(String username) {
        verifyExceptionMessages(gatherExceptionMessages(List.of(
                userValidator.checkIfUserExists(username)
        )));
    }

    public void validateTweetUpdateAction(String username, UUID tweetId) {
        verifyExceptionMessages(gatherExceptionMessages(List.of(
                userValidator.checkIfUserExists(username),
                tweetValidator.checkIfTweetExists(tweetId)
        )));
    }

    public void validateTweetLikeAction(String username, UUID tweetId) {
        verifyExceptionMessages(gatherExceptionMessages(List.of(
                userValidator.checkIfUserExists(username),
                tweetValidator.checkIfTweetExists(tweetId)
        )));
    }

    public void validateTweetReplyAction(String username, UUID mainTweetId, UUID tweetThreadId) {
        verifyExceptionMessages(gatherExceptionMessages(List.of(
                userValidator.checkIfUserExists(username),
                tweetValidator.checkIfTweetExists(mainTweetId),
                tweetValidator.checkIfTweetThreadExists(tweetThreadId)
        )));
    }

    public void validateTweetDeleteAction(String username, UUID tweetId) {
        verifyExceptionMessages(gatherExceptionMessages(List.of(
                userValidator.checkIfUserExists(username),
                tweetValidator.checkIfTweetExists(tweetId)
        )));
    }

    private void verifyExceptionMessages(List<String> existingValidationMessages) {
        if (!existingValidationMessages.isEmpty()) {
            throw new FailedTweetValidationException(existingValidationMessages);
        }
    }

    private List<String> gatherExceptionMessages(List<Optional<String>> performedValidationMessages) {
        return performedValidationMessages
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
