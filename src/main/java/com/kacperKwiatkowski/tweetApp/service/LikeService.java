package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.model.LikeEntity;
import com.kacperKwiatkowski.tweetApp.repository.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class LikeService {

    private final LikeRepository likeRepository;

    public void saveLike(String username, UUID tweetId) {
        if(likeRepository.existsByUsernameAndTweetId(username, tweetId)){
            likeRepository.deleteByUsernameAndTweetId(username, tweetId);
        } else {
            likeRepository.save(new LikeEntity(UUID.randomUUID(), username, tweetId));
        }
    }

    public long getLikesCountForTweet(UUID tweetId) {
        return likeRepository.countAllByTweetId(tweetId);
    }

    public void deleteAllByTweetId(UUID id) {
        likeRepository.deleteAllByTweetId(id);
    }

    public void deleteAllByTweetIds(List<UUID> tweetIdsToDelete) {
        tweetIdsToDelete.forEach(likeRepository::deleteAllByTweetId);
    }
}
