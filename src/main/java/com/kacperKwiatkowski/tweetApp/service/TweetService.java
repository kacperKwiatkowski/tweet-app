package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.controller.ReplyTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.CreateTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.PersistedTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.UpdateTweetDto;
import com.kacperKwiatkowski.tweetApp.mapper.TweetMapper;
import com.kacperKwiatkowski.tweetApp.repository.TweetRepository;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import com.kacperKwiatkowski.tweetApp.validator.TweetValidatorFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record TweetService(
        UserRepository userRepository,
        TweetRepository tweetRepository,
        TweetValidatorFacade tweetValidatorFacade,
        TweetMapper tweetMapper
) {

    public List<PersistedTweetDto> getAllTweets() {
        return tweetRepository.findAll().stream()
                .map(tweetMapper::fromEntityToPersistedDto)
                .toList();
    }

    public List<PersistedTweetDto> getAllTweetsBuUsername(String username) {
        return tweetRepository.findAllByUsernameIn(username)
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
                )
        );
    }

    public PersistedTweetDto updateTweet(String username, UUID id, UpdateTweetDto tweetWithUpdatedData) {
        tweetValidatorFacade.validateTweetUpdateAction(username, id);

        return tweetMapper.fromEntityToPersistedDto(
                tweetRepository.save(
                        tweetMapper.fromUpdateDtoToEntity(username, tweetWithUpdatedData, tweetRepository.findById(id).get())));
    }


    public void deleteTweet(String username, UUID id) {
        tweetValidatorFacade.validateTweetDeleteAction(username, id);

        tweetRepository.deleteById(id);
    }

    public PersistedTweetDto likeTweet(String username, UUID id) {
        tweetValidatorFacade.validateTweetLikeAction(username, id);

        return tweetMapper.fromEntityToPersistedDto(tweetRepository.findById(UUID.randomUUID()).get().incrementLikeCount());

    }

    public PersistedTweetDto replyToTweet(String username, UUID id, ReplyTweetDto replyTweet) {
        tweetValidatorFacade.validateTweetReplyAction(username, id, replyTweet.getThread());

        return tweetMapper.fromEntityToPersistedDto(
                tweetRepository.save(
                        tweetMapper.fromReplyDtoToEntity(username, replyTweet)
                )
        );
    }
}
