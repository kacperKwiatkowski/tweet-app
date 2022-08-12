package com.kacperKwiatkowski.tweetApp.validator;

import com.kacperKwiatkowski.tweetApp.exception.exceptions.FailedTweetValidationException;
import com.kacperKwiatkowski.tweetApp.repository.TweetRepository;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public record TweetValidatorFacade(
        UserRepository userRepository,
        TweetRepository tweetRepository
) {
    private static final String USER_NON_EXISTENT_EXCEPTION_MESSAGE = "Following user doesn't exists in the repository: ";
    private static final String TWEET_NON_EXISTENT_EXCEPTION_MESSAGE = "Following tweet doesn't exists in the repository: ";
    private static final String TWEET_THREAD_NON_EXISTENT_EXCEPTION_MESSAGE = "Following tweet thread doesn't exists in the repository: ";

    public void validateTweetSaveAction(String username) {

        List<Optional<String>> performedValidationMessages = List.of(
                checkIfUserExists(username)
        );

        verifyExceptionMessages(gatherExceptionMessages(performedValidationMessages));
    }

    public void validateTweetUpdateAction(String username, UUID tweetId) {

        List<Optional<String>> performedValidationMessages = List.of(
                checkIfUserExists(username),
                checkIfTweetExists(tweetId)
        );

        verifyExceptionMessages(gatherExceptionMessages(performedValidationMessages));
    }

    public void validateTweetLikeAction(String username, UUID tweetId) {

        List<Optional<String>> performedValidationMessages = List.of(
                checkIfUserExists(username),
                checkIfTweetExists(tweetId)
        );

        verifyExceptionMessages(
                gatherExceptionMessages(
                        performedValidationMessages));
    }

    public void validateTweetReplyAction(String username, UUID mainTweetId, UUID tweetThreadId) {

        List<Optional<String>> performedValidationMessages = List.of(
                checkIfUserExists(username),
                checkIfTweetExists(mainTweetId),
                checkIfTweetThreadExists(tweetThreadId)
        );

        verifyExceptionMessages(gatherExceptionMessages(performedValidationMessages));
    }

    public void validateTweetDeleteAction(String username, UUID tweetId) {

        List<Optional<String>> performedValidationMessages = List.of(
                checkIfUserExists(username),
                checkIfTweetExists(tweetId)
        );

        verifyExceptionMessages(gatherExceptionMessages(performedValidationMessages));
    }


    private Optional<String> checkIfUserExists(String username) {
        return userRepository.existsByUsername(username) ? Optional.empty() : Optional.of(USER_NON_EXISTENT_EXCEPTION_MESSAGE + username);
    }

    private Optional<String> checkIfTweetExists(UUID tweetId) {
        return tweetRepository.existsById(tweetId) ? Optional.empty() : Optional.of(TWEET_NON_EXISTENT_EXCEPTION_MESSAGE + tweetId);
    }

    private Optional<String> checkIfTweetThreadExists(UUID tweetThreadId) {
        return tweetRepository.existsByThreadId(tweetThreadId) ? Optional.empty() : Optional.of(TWEET_THREAD_NON_EXISTENT_EXCEPTION_MESSAGE + tweetThreadId);
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
