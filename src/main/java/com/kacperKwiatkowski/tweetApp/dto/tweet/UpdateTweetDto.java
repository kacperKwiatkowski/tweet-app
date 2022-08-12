package com.kacperKwiatkowski.tweetApp.dto.tweet;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateTweetDto {

    private String title;
    private String message;
}
