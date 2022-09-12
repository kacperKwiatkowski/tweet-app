package com.kacperKwiatkowski.tweetApp.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@DynamoDBTable(tableName = "tweet")
public class TweetEntity {

    @Value("${time.format.pattern}")
    private static String pattern = "dd.MM.yyyy HH:mm:ss:SSS";

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private UUID tweetId;
    @DynamoDBAttribute
    private String username;
    @DynamoDBAttribute
    private String avatar;
    @DynamoDBAttribute
    private String title;
    @DynamoDBAttribute
    private String message;
    @DynamoDBAttribute
    private String postDateTime;
    @DynamoDBAttribute
    private UUID threadId;

    public TweetEntity assignNewTweetData() {
        this.threadId = UUID.randomUUID();
        return this;
    }

    public TweetEntity assignNewReplyData() {
        this.tweetId = UUID.randomUUID();
        return this;
    }

    public TweetEntity assignPostTime() {
        this.postDateTime = LocalDateTime.parse(LocalDateTime.now().toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME).format(DateTimeFormatter.ofPattern(pattern));
        return this;
    }
}
