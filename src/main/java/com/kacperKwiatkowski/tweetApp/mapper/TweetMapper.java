package com.kacperKwiatkowski.tweetApp.mapper;

import com.kacperKwiatkowski.tweetApp.dto.tweet.CreateTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.ExtendedTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.ReplyTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.UpdateTweetDto;
import com.kacperKwiatkowski.tweetApp.model.TweetEntity;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.UUID;

@AllArgsConstructor
@Component
public class TweetMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public ExtendedTweetDto mapExtendedTweetDto(TweetEntity tweetToConvert, UserEntity userToConvert, long likeCount) {
        ExtendedTweetDto extendedTweetToMap = modelMapper.map(tweetToConvert, ExtendedTweetDto.class);
        extendedTweetToMap.setFirstName(userToConvert.getFirstName());
        extendedTweetToMap.setLastName(userToConvert.getLastName());
        extendedTweetToMap.setUsername(userToConvert.getUsername());
        extendedTweetToMap.setLikeCount(likeCount);
        extendedTweetToMap.setAvatar(Base64.getEncoder().encodeToString(userToConvert.getAvatar().getData()));
        return extendedTweetToMap;
    }

    public ExtendedTweetDto fromEntityToPersistedDto(TweetEntity tweetToConvert) {
        return modelMapper.map(tweetToConvert, ExtendedTweetDto.class);
    }

    public TweetEntity fromPersistedDtoToEntity(ExtendedTweetDto tweetToConvert) {
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
