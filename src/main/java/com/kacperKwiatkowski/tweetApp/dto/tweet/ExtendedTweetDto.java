package com.kacperKwiatkowski.tweetApp.dto.tweet;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExtendedTweetDto implements Comparable<ExtendedTweetDto> {

    @Value("${time.format.pattern}")
    private static String pattern = "dd.MM.yyyy HH:mm:ss:SSS";

    private UUID tweetId;
    private String username;
    private String firstName;
    private String lastName;
    private String avatar;
    private String title;
    private String message;
    private Long likeCount;
    private String postDateTime;
    private UUID threadId;

    @Override
    public int compareTo(ExtendedTweetDto o) {
        if (LocalDateTime.parse(this.postDateTime, DateTimeFormatter.ofPattern(pattern)).isAfter(LocalDateTime.parse(o.postDateTime, DateTimeFormatter.ofPattern(pattern)))) {
            return 1;
        }
        if (LocalDateTime.parse(this.postDateTime, DateTimeFormatter.ofPattern(pattern)).isBefore(LocalDateTime.parse(o.postDateTime, DateTimeFormatter.ofPattern(pattern)))) {
            return -1;
        } else return 0;
    }
}