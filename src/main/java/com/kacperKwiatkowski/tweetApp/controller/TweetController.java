package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TweetController {

    private final TweetService tweetService;

    @GetMapping("/all")
    void getAllTweets(){

    }

    @GetMapping("/{username}")
    void getAllTweetsByUsername(@PathVariable String username){

    }

    @PostMapping("/{username}/add")
    void postTweet(@PathVariable String username){

    }

    @PutMapping("/{username}/update/{id}")
    void UpdateTweet(@PathVariable String username, @PathVariable String id){

    }

    @DeleteMapping("/{username}/delete/{id}")
    void DeleteTweet(@PathVariable String username, @PathVariable String id){

    }

    @GetMapping("/{username}/like/{id}")
    void PutMapping(@PathVariable String username, @PathVariable String id){

    }

    @PostMapping("/{username}/reply/{id}")
    void ReplyToTweet(@PathVariable String username, @PathVariable String id){

    }
}
