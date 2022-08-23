package com.kacperKwiatkowski.tweetApp.validator;

import com.kacperKwiatkowski.tweetApp.repository.LikeRepository;
import com.kacperKwiatkowski.tweetApp.repository.TweetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class TweetValidator {

    private final TweetRepository tweetRepository;
    private final LikeRepository likeRepository;

    private static final String TWEET_NON_EXISTENT_EXCEPTION_MESSAGE = "Following tweet doesn't exists in the repository: ";
    private static final String TWEET_THREAD_NON_EXISTENT_EXCEPTION_MESSAGE = "Following tweet thread doesn't exists in the repository: ";
    private static final String TWEET_ALREADY_LIKED_BY_USER_EXCEPTION_MESSAGE = "You have already liked the tweet";
    public Optional<String> checkIfTweetExists(UUID tweetId) {
        return tweetRepository.existsById(tweetId) ? Optional.empty() : Optional.of(TWEET_NON_EXISTENT_EXCEPTION_MESSAGE + tweetId);
    }

    public Optional<String> checkIfTweetThreadExists(UUID tweetThreadId) {
        return tweetRepository.existsByThreadId(tweetThreadId) ? Optional.empty() : Optional.of(TWEET_THREAD_NON_EXISTENT_EXCEPTION_MESSAGE + tweetThreadId);
    }

    public Optional<String> checkIfTweetAlreadyLiked(String username, UUID tweetId) {
        return !likeRepository.existsByUsernameAndTweetId(username, tweetId) ? Optional.empty() : Optional.of(TWEET_ALREADY_LIKED_BY_USER_EXCEPTION_MESSAGE);

    }
}
