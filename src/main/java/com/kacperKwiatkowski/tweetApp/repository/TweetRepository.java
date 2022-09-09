package com.kacperKwiatkowski.tweetApp.repository;

import com.kacperKwiatkowski.tweetApp.model.TweetEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@EnableScan
@EnableScanCount
public interface TweetRepository extends CrudRepository<TweetEntity, UUID> {

    List<TweetEntity> findAllByUsernameContaining(String usernames);

    boolean existsByThreadId(UUID threadId);

    List<TweetEntity> findAllByThreadId(UUID threadId);

    boolean existsByThreadIdAndPostDateTimeBefore(UUID threadId, String postDateTime);
}
