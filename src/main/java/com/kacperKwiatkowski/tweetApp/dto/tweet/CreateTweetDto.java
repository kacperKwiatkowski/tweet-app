package com.kacperKwiatkowski.tweetApp.dto.tweet;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateTweetDto {

    private String title;
    private String message;
    private LocalDateTime postDateTime;
}
