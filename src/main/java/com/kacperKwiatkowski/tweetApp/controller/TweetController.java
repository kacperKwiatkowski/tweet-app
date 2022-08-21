package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.dto.tweet.*;
import com.kacperKwiatkowski.tweetApp.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
class TweetController {

    private final TweetService tweetService;

    @PreAuthorize("hasAuthority('level:auth')")
    @GetMapping("/all")
    WallDto getAllTweets() {
        return tweetService.getAllTweets();
    }

    @PreAuthorize("hasAuthority('level:auth')")
    @GetMapping("/{username}")
    List<PersistedTweetDto> getAllTweetsByUsername(@PathVariable String username) {
        return tweetService.getAllTweetsByUsername(username);
    }

    @PreAuthorize("hasAuthority('level:auth')")
    @PostMapping("/{username}/add")
    @ResponseStatus(HttpStatus.CREATED)
    PersistedTweetDto saveTweet(@PathVariable String username, @Valid @RequestBody CreateTweetDto tweetToSave) {
        return tweetService.saveTweet(username, tweetToSave);
    }

    @PreAuthorize("hasAuthority('level:auth')")
    @PutMapping("/{username}/update/{id}")
    PersistedTweetDto updateTweet(@PathVariable String username, @PathVariable UUID id, @RequestBody UpdateTweetDto tweetToUpdate) {
        return tweetService.updateTweet(username, id, tweetToUpdate);
    }

    @PreAuthorize("hasAuthority('level:auth')")
    @DeleteMapping("/{username}/delete/{id}")
    void deleteTweet(@PathVariable String username, @PathVariable UUID id) {
        tweetService.deleteTweet(username, id);
    }

    @PreAuthorize("hasAuthority('level:auth')")
    @PutMapping("/{username}/like/{id}")
    PersistedTweetDto likeTweet(@PathVariable String username, @PathVariable UUID id) {
        return tweetService.likeTweet(username, id);
    }

    @PreAuthorize("hasAuthority('level:auth')")
    @PostMapping("/{username}/reply/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    PersistedTweetDto replyToTweet(@PathVariable String username, @PathVariable UUID id, @RequestBody ReplyTweetDto replyTweet) {
        return tweetService.replyToTweet(username, id, replyTweet);
    }
}
