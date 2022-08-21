package com.kacperKwiatkowski.tweetApp.dto.tweet;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReplyTweetDto {

    @NotEmpty(message = "Can't be empty")
    @Size(max = 40, message = "Can't be longer than 40 characters")
    private String title;

    @NotEmpty(message = "Can't be empty")
    @Size(max = 160, message = "Can't be longer than 160 characters")
    private String message;

    private UUID threadId;
}
