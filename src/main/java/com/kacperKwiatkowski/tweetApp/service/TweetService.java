package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.dto.tweet.*;
import com.kacperKwiatkowski.tweetApp.mapper.TweetMapper;
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
    private final TweetMapper tweetMapper;

    private final WallService wallService;

    public WallDto getAllTweets() {
        return wallService.arrangeWall(
                tweetRepository.findAll().stream()
                        .map(tweetMapper::fromEntityToPersistedDto)
                        .toList()
        );
    }

    public List<PersistedTweetDto> getAllTweetsByUsername(String username) {
        return tweetRepository.findAllByUsernameContaining(username)
                .stream()
                .map(tweetMapper::fromEntityToPersistedDto)
                .toList();
    }

    public PersistedTweetDto saveTweet(String username, CreateTweetDto tweetToSave) {
        tweetValidatorFacade.validateTweetSaveAction(username);

        return tweetMapper.fromEntityToPersistedDto(
                tweetRepository.save(
                        tweetMapper.fromCreateDtoToEntity(username, tweetToSave)
                                .assignNewTweetData()
                                .assignPostTime()
                )
        );
    }

    public PersistedTweetDto updateTweet(String username, UUID tweetId, UpdateTweetDto tweetWithUpdatedData) {
        tweetValidatorFacade.validateTweetUpdateAction(username, tweetId);

        return tweetMapper.fromEntityToPersistedDto(
                tweetRepository.save(
                        tweetMapper.fromUpdateDtoToEntity(username, tweetId, tweetWithUpdatedData)));
    }


    public void deleteTweet(String username, UUID id) {
        tweetValidatorFacade.validateTweetDeleteAction(username, id);

        tweetRepository.deleteById(id);
    }

    public PersistedTweetDto likeTweet(String username, UUID tweetId) {
        tweetValidatorFacade.validateTweetLikeAction(username, tweetId);

        TweetEntity tweetToIncreaseLikeCount = tweetRepository.findById(tweetId).get().incrementLikeCount();

        return tweetMapper.fromEntityToPersistedDto(tweetRepository.save(tweetToIncreaseLikeCount));
    }

    public PersistedTweetDto replyToTweet(String username, UUID mainTweetId, ReplyTweetDto replyTweet) {
        tweetValidatorFacade.validateTweetReplyAction(username, mainTweetId, replyTweet.getThreadId());

        TweetEntity replyTweetEntity = tweetMapper.fromReplyDtoToEntity(username, replyTweet).assignNewReplyTweetData();

        return tweetMapper.fromEntityToPersistedDto(
                tweetRepository.save(
                        replyTweetEntity
                                .assignPostTime()
                )
        );
    }
}
