package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.dto.TweetDto;
import com.kacperKwiatkowski.tweetApp.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
class TweetController {

    private final TweetService tweetService;

    @PreAuthorize("hasAuthority('level:auth')")
    @GetMapping("/all")
    List<TweetDto> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @PreAuthorize("hasAuthority('level:auth')")
    @GetMapping("/{username}")
    List<TweetDto> getAllTweetsByUsername(@PathVariable String username) {
        return tweetService.getAllTweetsBuUsername(username);
    }

    @PreAuthorize("hasAuthority('level:auth')")
    @PostMapping("/{username}/add")
    @ResponseStatus(HttpStatus.CREATED)
    void saveTweet(@PathVariable String username) {
        tweetService.saveTweet();
    }

    @PreAuthorize("hasAuthority('level:auth')")
    @PutMapping("/{username}/update/{id}")
    TweetDto updateTweet(@PathVariable String username, @PathVariable String id) {
        return tweetService.updateTweet();
    }

    @PreAuthorize("hasAuthority('level:auth')")
    @DeleteMapping("/{username}/delete/{id}")
    void deleteTweet(@PathVariable String username, @PathVariable String id) {
        tweetService.deleteTweet();
    }

    @PreAuthorize("hasAuthority('level:auth')")
    @GetMapping("/{username}/like/{id}")
    TweetDto likeTweet(@PathVariable String username, @PathVariable String id) {
        return tweetService.likeTweet();
    }

    @PreAuthorize("hasAuthority('level:auth')")
    @PostMapping("/{username}/reply/{id}")
    TweetDto replyToTweet(@PathVariable String username, @PathVariable String id) {
        return tweetService.replyToTweet();
    }
}
