package com.kacperKwiatkowski.tweetApp.mapper;

import com.kacperKwiatkowski.tweetApp.dto.tweet.CreateTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.PersistedTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.ReplyTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.UpdateTweetDto;
import com.kacperKwiatkowski.tweetApp.model.TweetEntity;
import com.kacperKwiatkowski.tweetApp.util.TweetObjectProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TweetMapperTest {

    private static final String USERNAME = "USERNAME";
    private static final UUID TWEET_ID = UUID.randomUUID();

    @InjectMocks
    private TweetMapper tweetMapper;

    @Test
    void shouldMapEntityToPersistedDto() {
        // given
        TweetEntity tweetToMap = TweetObjectProvider.provideTweetEntity();

        // when
        PersistedTweetDto mappedTweet = tweetMapper.fromEntityToPersistedDto(tweetToMap);

        // then
        assertEquals(tweetToMap.getTweetId(), mappedTweet.getTweetId());
        assertEquals(tweetToMap.getTitle(), mappedTweet.getTitle());
        assertEquals(tweetToMap.getMessage(), mappedTweet.getMessage());
        assertEquals(tweetToMap.getLikeCount(), mappedTweet.getLikeCount());
        assertEquals(tweetToMap.getPostDateTime(), mappedTweet.getPostDateTime());
        assertEquals(tweetToMap.getThreadId(), mappedTweet.getThreadId());

    }

    @Test
    void shouldMapPersistedDtoToEntity() {
        // given
        PersistedTweetDto tweetToMap = TweetObjectProvider.providePersistedTweetDto();

        // when
        TweetEntity mappedTweet = tweetMapper.fromPersistedDtoToEntity(tweetToMap);

        // then
        assertEquals(tweetToMap.getTweetId(), mappedTweet.getTweetId());
        assertEquals(tweetToMap.getTitle(), mappedTweet.getTitle());
        assertEquals(tweetToMap.getMessage(), mappedTweet.getMessage());
        assertEquals(tweetToMap.getLikeCount(), mappedTweet.getLikeCount());
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
        assertEquals(tweetToMap.getPostDateTime(), mappedTweet.getPostDateTime());
    }

    @Test
    void shouldMapUpdateDtoToEntity() {
        // given
        UpdateTweetDto tweetToMap = TweetObjectProvider.provideUpdateTweetDto();

        // when
        TweetEntity mappedTweet = tweetMapper.fromUpdateDtoToEntity(USERNAME, TWEET_ID, tweetToMap);

        // then
        assertEquals(tweetToMap.getTitle(), mappedTweet.getTitle());
        assertEquals(tweetToMap.getMessage(), mappedTweet.getMessage());
        assertEquals(TWEET_ID, mappedTweet.getTweetId());
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
        assertEquals(tweetToMap.getPostDateTime(), mappedTweet.getPostDateTime());
        assertEquals(tweetToMap.getThreadId(), mappedTweet.getThreadId());
        assertEquals(USERNAME, mappedTweet.getUsername());
    }
}