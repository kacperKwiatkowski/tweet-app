package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.dto.tweet.ExtendedTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.ThreadDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.WallDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@Component
public class WallService {

    public WallDto arrangeWall(List<ExtendedTweetDto> allTweets) {

        Map<UUID, ThreadDto> threadsMap = organiseTweetsInThreads(allTweets);

        List<ThreadDto> threads = threadsMap.values().stream().toList();

        return new WallDto(threads.stream().map(ThreadDto::sortThreadByPostTime).toList()).sortThreadByPostTime();
    }

    public ThreadDto arrangeThread(List<ExtendedTweetDto> allTweets) {
        return new ThreadDto(allTweets).sortThreadByPostTime();
    }

    private Map<UUID, ThreadDto> organiseTweetsInThreads(List<ExtendedTweetDto> allTweets) {
        Map<UUID, ThreadDto> threadsMap = new TreeMap<>();

        allTweets.forEach(tweet -> assignTweetToThread(tweet, threadsMap));

        return threadsMap;
    }

    private void assignTweetToThread(ExtendedTweetDto tweet, Map<UUID, ThreadDto> threadsMap) {
        if (threadsMap.containsKey(tweet.getThreadId())) {
            ThreadDto foundThread = threadsMap.get(tweet.getThreadId());
            foundThread.assignTweetToThread(tweet);
            threadsMap.put(tweet.getThreadId(), foundThread);
            return;
        }

        threadsMap.put(tweet.getThreadId(), new ThreadDto(List.of(tweet)));
    }
}
