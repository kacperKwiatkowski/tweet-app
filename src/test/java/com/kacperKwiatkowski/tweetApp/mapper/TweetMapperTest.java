package com.kacperKwiatkowski.tweetApp.mapper;

import com.kacperKwiatkowski.tweetApp.dto.tweet.CreateTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.ExtendedTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.ReplyTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.UpdateTweetDto;
import com.kacperKwiatkowski.tweetApp.model.TweetEntity;
import com.kacperKwiatkowski.tweetApp.util.TweetObjectProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class TweetMapperTest {

    private static final String USERNAME = "USERNAME";

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private TweetMapper tweetMapper;

    @Test
    void shouldMapEntityToPersistedDto() {
        // given
        TweetEntity tweetToMap = TweetObjectProvider.provideTweetEntity();

        // when
        ExtendedTweetDto mappedTweet = tweetMapper.fromEntityToPersistedDto(tweetToMap);

        // then
        assertEquals(tweetToMap.getTweetId(), mappedTweet.getTweetId());
        assertEquals(tweetToMap.getTitle(), mappedTweet.getTitle());
        assertEquals(tweetToMap.getMessage(), mappedTweet.getMessage());
        assertEquals(tweetToMap.getPostDateTime().toString(), mappedTweet.getPostDateTime());
        assertEquals(tweetToMap.getThreadId(), mappedTweet.getThreadId());
    }

    @Test
    void shouldMapPersistedDtoToEntity() {
        // given
        ExtendedTweetDto tweetToMap = TweetObjectProvider.providePersistedTweetDto();

        // when
        TweetEntity mappedTweet = tweetMapper.fromExtendedDtoToEntity(tweetToMap);

        // then
        assertEquals(tweetToMap.getTweetId(), mappedTweet.getTweetId());
        assertEquals(tweetToMap.getTitle(), mappedTweet.getTitle());
        assertEquals(tweetToMap.getMessage(), mappedTweet.getMessage());
        assertEquals(tweetToMap.getPostDateTime(), mappedTweet.getPostDateTime());
        assertEquals(tweetToMap.getThreadId(), mappedTweet.getThreadId());
    }

    @Test
    void shouldMapCreateDtoToEntity() {
        // given
        CreateTweetDto tweetToMap = TweetObjectProvider.provideCreateTweetDto();

        // when
        TweetEntity mappedTweet = tweetMapper.fromCreateDtoToEntity(USERNAME, tweetToMap);

        // then
        assertEquals(tweetToMap.getTitle(), mappedTweet.getTitle());
        assertEquals(tweetToMap.getMessage(), mappedTweet.getMessage());
    }

    @Test
    void shouldMapUpdateDtoToEntity() {
        // given
        TweetEntity tweetToUpdate = TweetObjectProvider.provideTweetEntity();
        UpdateTweetDto tweetToMap = TweetObjectProvider.provideUpdateTweetDto();

        // when
        TweetEntity mappedTweet = tweetMapper.fromUpdateDtoToEntity(tweetToUpdate, tweetToMap);

        // then
        assertEquals(tweetToMap.getTitle(), mappedTweet.getTitle());
        assertEquals(tweetToMap.getMessage(), mappedTweet.getMessage());
        assertEquals(tweetToUpdate.getTweetId(), mappedTweet.getTweetId());
    }

    @Test
    void shouldMapReplyDtoToEntity() {
        // given
        ReplyTweetDto tweetToMap = TweetObjectProvider.provideReplyTweetDto();

        // when
        TweetEntity mappedTweet = tweetMapper.fromReplyDtoToEntity(USERNAME, tweetToMap);

        // then
        assertEquals(tweetToMap.getTitle(), mappedTweet.getTitle());
        assertEquals(tweetToMap.getMessage(), mappedTweet.getMessage());
        assertEquals(tweetToMap.getThreadId(), mappedTweet.getThreadId());
        assertEquals(USERNAME, mappedTweet.getUsername());
    }
}