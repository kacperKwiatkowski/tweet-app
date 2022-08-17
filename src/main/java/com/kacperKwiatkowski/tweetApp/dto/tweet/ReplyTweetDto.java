package com.kacperKwiatkowski.tweetApp.dto.tweet;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReplyTweetDto {

    private String title;
    private String message;
    private LocalDateTime postDateTime;
    private UUID threadId;
}
