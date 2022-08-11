package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.dto.TweetDto;
import com.kacperKwiatkowski.tweetApp.mapper.TweetMapper;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import com.kacperKwiatkowski.tweetApp.repository.TweetRepository;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record TweetService(
        UserRepository userRepository,
        TweetRepository tweetRepository,
        TweetMapper tweetMapper
) {

    public List<TweetDto> getAllTweets() {
        return tweetRepository.findAll().stream()
                .map(tweetMapper::toDto)
                .toList();
    }

    public List<TweetDto> getAllTweetsBuUsername(String username) {
        List<UUID> usersIds = userRepository.findAllByUsernameContaining(username)
                .stream()
                .map(UserEntity::getId)
                .toList();

        return tweetRepository.findAllByUserIdIn(usersIds)
                .stream()
                .map(tweetMapper::toDto)
                .toList();
    }

    public void saveTweet() {

    }

    public TweetDto updateTweet() {
        return null;
    }

    public void deleteTweet() {
    }

    public TweetDto likeTweet() {
        return null;
    }

    public TweetDto replyToTweet() {
        return null;
    }
}
