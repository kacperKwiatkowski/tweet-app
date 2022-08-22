package com.kacperKwiatkowski.tweetApp.dto.tweet;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThreadDto implements Comparable<ThreadDto> {

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
        if (this.getTweets().get(0).getPostDateTime().isBefore(o.getTweets().get(0).getPostDateTime())) return 1;
        if (this.getTweets().get(0).getPostDateTime().isAfter(o.getTweets().get(0).getPostDateTime())) return -1;
        else return 0;
    }
}
