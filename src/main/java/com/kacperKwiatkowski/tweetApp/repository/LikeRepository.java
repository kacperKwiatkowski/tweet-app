package com.kacperKwiatkowski.tweetApp.repository;

import com.kacperKwiatkowski.tweetApp.model.LikeEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

@EnableScan
@EnableScanCount
public interface LikeRepository extends CrudRepository<LikeEntity, UUID> {

    void deleteByUsernameAndTweetId(String username, UUID tweetId);

    long countAllByTweetId(UUID tweetId);

    boolean existsByUsernameAndTweetId(String username, UUID tweetId);

    void deleteAllByTweetId(UUID tweetId);
}
