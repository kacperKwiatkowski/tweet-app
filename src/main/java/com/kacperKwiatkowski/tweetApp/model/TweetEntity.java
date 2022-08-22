package com.kacperKwiatkowski.tweetApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document("tweets")
public class TweetEntity {

    @Id
    private UUID tweetId;
    private String username;
    private String avatar;
    private String title;
    private String message;
    private LocalDateTime postDateTime;

    //TODO Assign thread id if new tweet
    private UUID threadId;

    //TODO Do it in DTOs
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
        this.postDateTime = LocalDateTime.now();
        return this;
    }
}
