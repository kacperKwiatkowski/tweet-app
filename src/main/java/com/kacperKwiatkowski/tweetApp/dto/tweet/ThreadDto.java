package com.kacperKwiatkowski.tweetApp.dto.tweet;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThreadDto implements Comparable<ThreadDto> {

    @Value("${time.format.pattern}")
    private static String pattern = "dd.MM.yyyy HH:mm:ss:SSS";

    private List<ExtendedTweetDto> tweets;

    public void assignTweetToThread(ExtendedTweetDto tweetToAssign) {
        List<ExtendedTweetDto> tweetDtoList = new ArrayList<>(this.tweets);
        tweetDtoList.add(tweetToAssign);
        this.tweets = tweetDtoList;
    }

    public ThreadDto sortThreadByPostTime() {
        List<ExtendedTweetDto> tweetDtoList = new ArrayList<>(this.tweets);
        Collections.sort(tweetDtoList);
        this.tweets = tweetDtoList;
        return this;
    }

    @Override
    public int compareTo(ThreadDto o) {
        if (LocalDateTime.parse(this.getTweets().get(0).getPostDateTime(), DateTimeFormatter.ofPattern(pattern)).isBefore(LocalDateTime.parse(o.getTweets().get(0).getPostDateTime(), DateTimeFormatter.ofPattern(pattern))))
            return 1;
        if (LocalDateTime.parse(this.getTweets().get(0).getPostDateTime(), DateTimeFormatter.ofPattern(pattern)).isAfter(LocalDateTime.parse(o.getTweets().get(0).getPostDateTime(), DateTimeFormatter.ofPattern(pattern))))
            return -1;
        else return 0;
    }




}
