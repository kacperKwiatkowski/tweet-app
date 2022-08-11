package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.repository.TweetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TweetService {

    private final TweetRepository tweetRepository;
}
