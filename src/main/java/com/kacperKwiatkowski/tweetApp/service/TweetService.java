package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.dto.tweet.*;
import com.kacperKwiatkowski.tweetApp.mapper.TweetMapper;
import com.kacperKwiatkowski.tweetApp.mapper.UserMapper;
import com.kacperKwiatkowski.tweetApp.model.TweetEntity;
import com.kacperKwiatkowski.tweetApp.repository.TweetRepository;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import com.kacperKwiatkowski.tweetApp.validator.TweetValidatorFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TweetService {

    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    private final TweetValidatorFacade tweetValidatorFacade;

    private final UserMapper userMapper;
    private final TweetMapper tweetMapper;

    private final LikeService likeService;
    private final WallService wallService;

    public ExtendedTweetDto getTweetById(UUID tweetId) {
        tweetValidatorFacade.validateTweetGetAction(tweetId);

        return constructExtendedTweetDto(tweetRepository.findById(tweetId).get());
    }

    public ThreadDto getThreadById(UUID threadId) {

        tweetValidatorFacade.validateThreadGetAction(threadId);

        return wallService.arrangeThread(
                tweetRepository.findAllByThreadId(threadId).stream()
                        .map(this::constructExtendedTweetDto)
                        .toList()
        );
    }

    public WallDto getAllTweets() {
        return wallService.arrangeWall(
                tweetRepository.findAll().stream()
                        .map(this::constructExtendedTweetDto)
                        .toList()
        );
    }

    public List<ExtendedTweetDto> getAllTweetsByUsername(String username) {
        return tweetRepository.findAllByUsernameContaining(username)
                .stream()
                .map(this::constructExtendedTweetDto)
                .toList();
    }

    public ExtendedTweetDto saveTweet(String username, CreateTweetDto tweetToSave) {
        tweetValidatorFacade.validateTweetSaveAction(username);

        return constructExtendedTweetDto(
                tweetRepository.save(
                        tweetMapper.fromCreateDtoToEntity(username, tweetToSave)
                                .assignNewTweetData()
                                .assignPostTime()
                )
        );
    }

    public ExtendedTweetDto updateTweet(String username, UUID tweetId, UpdateTweetDto tweetWithUpdatedData) {
        tweetValidatorFacade.validateTweetUpdateAction(username, tweetId);

        return constructExtendedTweetDto(
                tweetRepository.save(
                        tweetMapper.fromUpdateDtoToEntity(username, tweetId, tweetWithUpdatedData)));
    }


    public void deleteTweet(String username, UUID id) {
        tweetValidatorFacade.validateTweetDeleteAction(username, id);

        tweetRepository.deleteById(id);
    }

    public void likeTweet(String username, UUID tweetId) {
        tweetValidatorFacade.validateTweetLikeAction(username, tweetId);

        likeService.saveLike(username, tweetId);
    }

    public ExtendedTweetDto replyToTweet(String username, UUID mainTweetId, ReplyTweetDto replyTweet) {
        tweetValidatorFacade.validateTweetReplyAction(username, mainTweetId, replyTweet.getThreadId());

        TweetEntity replyTweetEntity = tweetMapper.fromReplyDtoToEntity(username, replyTweet);

        return tweetMapper.fromEntityToPersistedDto(
                tweetRepository.save(
                        replyTweetEntity
                                .assignNewReplyData()
                                .assignPostTime()
                )
        );
    }

    private ExtendedTweetDto constructExtendedTweetDto(TweetEntity tweet) {
        return tweetMapper.mapExtendedTweetDto(
                tweet,
                userRepository.findUserEntityByUsername(tweet.getUsername()),
                likeService.getLikesCountForTweet(tweet.getTweetId())
        );
    }
}
