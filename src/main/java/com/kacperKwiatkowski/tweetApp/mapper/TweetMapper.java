package com.kacperKwiatkowski.tweetApp.mapper;

import com.kacperKwiatkowski.tweetApp.controller.ReplyTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.CreateTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.PersistedTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.UpdateTweetDto;
import com.kacperKwiatkowski.tweetApp.model.TweetEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TweetMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public PersistedTweetDto fromEntityToPersistedDto(TweetEntity tweetToConvert) {
        return modelMapper.map(tweetToConvert, PersistedTweetDto.class);
    }

    public TweetEntity fromPersistedDtoToEntity(PersistedTweetDto tweetToConvert) {
        return modelMapper.map(tweetToConvert, TweetEntity.class);
    }

    public TweetEntity fromCreateDtoToEntity(String username, CreateTweetDto tweetToConvert) {
        TweetEntity convertedTweet = modelMapper.map(tweetToConvert, TweetEntity.class);
        convertedTweet.setUsername(username);
        return convertedTweet;
    }

    public TweetEntity fromUpdateDtoToEntity(String username, UpdateTweetDto tweetToConvert, TweetEntity tweetToUpdate) {
        modelMapper.map(tweetToConvert, tweetToUpdate);
        return tweetToUpdate;
    }

    public TweetEntity fromReplyDtoToEntity(String username, ReplyTweetDto tweetToConvert) {
        TweetEntity convertedTweet = modelMapper.map(tweetToConvert, TweetEntity.class);
        convertedTweet.setUsername(username);
        return convertedTweet;
    }
}
