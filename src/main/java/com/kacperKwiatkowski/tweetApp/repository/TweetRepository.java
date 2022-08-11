package com.kacperKwiatkowski.tweetApp.repository;

import com.kacperKwiatkowski.tweetApp.model.TweetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TweetRepository extends MongoRepository<TweetEntity, UUID> {
}
