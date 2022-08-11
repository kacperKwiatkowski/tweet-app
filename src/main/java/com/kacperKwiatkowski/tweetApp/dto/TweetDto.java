package com.kacperKwiatkowski.tweetApp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TweetDto {

    private String userId;
    private String avatar;
    private String title;
    private String message;
    private LocalDateTime postDateTime;
}
