package com.kacperKwiatkowski.tweetApp.repository;

import com.kacperKwiatkowski.tweetApp.model.TweetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TweetRepository extends MongoRepository<TweetEntity, UUID> {

    List<TweetEntity> findAllByUsernameContaining(String usernames);

    boolean existsByThreadId(UUID threadId);

    List<TweetEntity> findAllByThreadId(UUID threadId);
}
