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
    private UUID id;
    private UUID userId;
    private String avatar;
    private String title;
    private String message;
    private LocalDateTime postDateTime;
}
