package com.kacperKwiatkowski.tweetApp.dto.tweet;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersistedTweetDto {

    @NotBlank
    private UUID tweetId;

    private String username;

    @NotBlank
    private String title;

    @NotBlank
    private String message;

    private Long likeCount;

    @NotBlank
    private LocalDateTime postDateTime;

    private UUID threadId;
}
