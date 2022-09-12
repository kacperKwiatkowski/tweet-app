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

    private static String PATTERN = "dd/MM/yyyy HH/mm/ss/SSS";


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
        if (LocalDateTime.parse(this.postDateTime, DateTimeFormatter.ofPattern(PATTERN)).isAfter(LocalDateTime.parse(o.postDateTime, DateTimeFormatter.ofPattern(PATTERN)))) {
            return 1;
        }
        if (LocalDateTime.parse(this.postDateTime, DateTimeFormatter.ofPattern(PATTERN)).isBefore(LocalDateTime.parse(o.postDateTime, DateTimeFormatter.ofPattern(PATTERN)))) {
            return -1;
        } else return 0;
    }
}