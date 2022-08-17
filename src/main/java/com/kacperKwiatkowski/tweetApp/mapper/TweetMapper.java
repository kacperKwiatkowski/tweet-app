package com.kacperKwiatkowski.tweetApp.mapper;

import com.kacperKwiatkowski.tweetApp.dto.tweet.CreateTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.PersistedTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.ReplyTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.UpdateTweetDto;
import com.kacperKwiatkowski.tweetApp.model.TweetEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

    public TweetEntity fromUpdateDtoToEntity(String username, UUID tweetId, UpdateTweetDto tweetToUpdate) {
        TweetEntity convertedTweet = modelMapper.map(tweetToUpdate, TweetEntity.class);
        convertedTweet.setTweetId(tweetId);
        return convertedTweet;
    }

    public TweetEntity fromReplyDtoToEntity(String username, ReplyTweetDto tweetToConvert) {
        TweetEntity convertedTweet = modelMapper.map(tweetToConvert, TweetEntity.class);
        convertedTweet.setUsername(username);
        return convertedTweet;
    }
}
