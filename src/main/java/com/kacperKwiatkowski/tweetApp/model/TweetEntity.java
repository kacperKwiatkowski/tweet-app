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
    private long likeCount;
    private LocalDateTime postDateTime;

    //TODO Assign thread id if new tweet
    private UUID threadId;

    //TODO Do it in DTOs
    public TweetEntity assignNewTweetData() {
        this.tweetId = UUID.randomUUID();
        this.likeCount = 0;
        this.threadId = UUID.randomUUID();
        return this;
    }

    public TweetEntity assignNewReplyTweetData() {
        this.likeCount = 0;
        return this;
    }

    public TweetEntity incrementLikeCount() {
        this.likeCount++;
        return this;
    }
}
