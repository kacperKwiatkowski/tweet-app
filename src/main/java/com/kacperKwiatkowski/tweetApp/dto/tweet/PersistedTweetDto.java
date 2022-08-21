package com.kacperKwiatkowski.tweetApp.dto.tweet;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersistedTweetDto implements Comparable<PersistedTweetDto> {

    private UUID tweetId;
    private String username;
    private String title;
    private String message;
    private Long likeCount;
    private LocalDateTime postDateTime;
    private UUID threadId;

    @Override
    public int compareTo(PersistedTweetDto o) {
        if (this.postDateTime.isBefore(o.postDateTime)) return 1;
        if (this.postDateTime.isAfter(o.postDateTime)) return -1;
        else return 0;
    }
}
