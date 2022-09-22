package com.kacperKwiatkowski.tweetApp.validator.tweet;

import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedTweetValidationException;
import com.kacperKwiatkowski.tweetApp.validator.user.UserValidator;
import com.kacperKwiatkowski.tweetApp.validator.common.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class TweetValidatorFacade {

    private final UserValidator userValidator;
    private final TweetValidator tweetValidator;
    private final ValidatorUtil validatorUtil;

    public void validateTweetGetAction(UUID tweetId) {
        verifyExceptionMessages(
                validatorUtil.gatherExceptionMessages(List.of(
                        tweetValidator.checkIfTweetExists(tweetId)
                )));
    }

    public void validateThreadGetAction(UUID threadId) {
        verifyExceptionMessages(
                validatorUtil.gatherExceptionMessages(List.of(
                        tweetValidator.checkIfTweetThreadExists(threadId)
                )));
    }

    public void validateTweetSaveAction(String username) {
        verifyExceptionMessages(
                validatorUtil.gatherExceptionMessages(List.of(
                        userValidator.checkIfUserDoesntExistsByUsername(username)
                )));
    }

    public void validateTweetUpdateAction(String username, UUID tweetId) {
        verifyExceptionMessages(
                validatorUtil.gatherExceptionMessages(List.of(
                        userValidator.checkIfUserDoesntExistsByUsername(username),
                        tweetValidator.checkIfTweetExists(tweetId)
                )));
    }

    public void validateTweetLikeAction(String username, UUID tweetId) {
        verifyExceptionMessages(
                validatorUtil.gatherExceptionMessages(List.of(
                        userValidator.checkIfUserDoesntExistsByUsername(username),
                        tweetValidator.checkIfTweetExists(tweetId)
                )));
    }

    public void validateTweetReplyAction(String username, UUID mainTweetId, UUID tweetThreadId) {
        verifyExceptionMessages(
                validatorUtil.gatherExceptionMessages(List.of(
                        userValidator.checkIfUserDoesntExistsByUsername(username),
                        tweetValidator.checkIfTweetExists(mainTweetId),
                        tweetValidator.checkIfTweetThreadExists(tweetThreadId)
                )));
    }

    public void validateTweetDeleteAction(String username, UUID tweetId) {
        verifyExceptionMessages(
                validatorUtil.gatherExceptionMessages(List.of(
                        userValidator.checkIfUserDoesntExistsByUsername(username),
                        tweetValidator.checkIfTweetExists(tweetId)
                )));
    }

    private void verifyExceptionMessages(List<String> existingValidationMessages) {
        if (!existingValidationMessages.isEmpty()) {
            throw new FailedTweetValidationException(existingValidationMessages);
        }
    }
}
