package com.kacperKwiatkowski.tweetApp.repository;

import com.kacperKwiatkowski.tweetApp.model.LikeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LikeRepository extends MongoRepository<LikeEntity, UUID> {

    void deleteByUsernameAndTweetId(String username, UUID tweetId);

    long countAllByTweetId(UUID tweetId);

    boolean existsByUsernameAndTweetId(String username, UUID tweetId);

    void deleteAllByTweetId(UUID tweetId);
}
