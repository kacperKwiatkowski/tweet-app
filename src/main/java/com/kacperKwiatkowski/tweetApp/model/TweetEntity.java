package com.kacperKwiatkowski.tweetApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document("tweets")
public class TweetEntity {
    private static final String PATTERN = "dd.MM.yyyy HH:mm:ss:SSS";
    @Id
    private UUID tweetId;
    private String username;
    private String avatar;
    private String title;
    private String message;
    private String postDateTime;
    private UUID threadId;

    public TweetEntity assignNewTweetData() {
        this.tweetId = UUID.randomUUID();
        this.threadId = UUID.randomUUID();
        return this;
    }

    public TweetEntity assignNewReplyData() {
        this.tweetId = UUID.randomUUID();
        return this;
    }

    public TweetEntity assignPostTime() {
        this.postDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN));
        return this;
    }
}
