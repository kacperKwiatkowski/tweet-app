package com.kacperKwiatkowski.tweetApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TweetDto {

    private String userId;
    private String avatar;
    private String title;
    private String message;
    private LocalDateTime postDateTime;
}
