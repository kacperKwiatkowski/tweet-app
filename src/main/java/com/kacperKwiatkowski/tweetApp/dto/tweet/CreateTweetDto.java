package com.kacperKwiatkowski.tweetApp.dto.tweet;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateTweetDto {

    @NotEmpty(message = "Can't be empty")
    @Size(max = 40, message = "Can't be longer than 40 characters")
    private String title;

    @NotEmpty(message = "Can't be empty")
    @Size(max = 160, message = "Can't be longer than 160 characters")
    private String message;

}
