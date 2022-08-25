package com.kacperKwiatkowski.tweetApp.util;

import com.kacperKwiatkowski.tweetApp.dto.tweet.CreateTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.ExtendedTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.ReplyTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.UpdateTweetDto;
import com.kacperKwiatkowski.tweetApp.model.TweetEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TweetObjectProvider {

    private static final String PATTERN = "dd.MM.yyyy HH:mm:ss:SSS";

    public static TweetEntity provideTweetEntity() {

        String randomFieldIndex = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        return TweetEntity.builder()
                .tweetId(UUID.randomUUID())
                .title(randomFieldIndex)
                .message(randomFieldIndex)
                .postDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN)))
                .threadId(UUID.randomUUID())
                .build();
    }

    public static CreateTweetDto provideCreateTweetDto() {

        String randomFieldIndex = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        return CreateTweetDto.builder()
                .title(randomFieldIndex)
                .message(randomFieldIndex)
                .build();
    }

    public static ExtendedTweetDto providePersistedTweetDto() {

        String randomFieldIndex = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        return ExtendedTweetDto.builder()
                .tweetId(UUID.randomUUID())
                .username(randomFieldIndex)
                .title(randomFieldIndex)
                .message(randomFieldIndex)
                .likeCount(Long.MIN_VALUE)
                .postDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN)))
                .threadId(UUID.randomUUID())
                .build();
    }

    public static ReplyTweetDto provideReplyTweetDto() {

        String randomFieldIndex = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        return ReplyTweetDto.builder()
                .title(randomFieldIndex)
                .message(randomFieldIndex)
                .threadId(UUID.randomUUID())
                .build();
    }

    public static UpdateTweetDto provideUpdateTweetDto() {

        String randomFieldIndex = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        return UpdateTweetDto.builder()
                .title(randomFieldIndex)
                .message(randomFieldIndex)
                .build();
    }
}
