package com.kacperKwiatkowski.tweetApp.repository;

import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@EnableScan
@EnableScanCount
public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    UserEntity findUserEntityByUsername(String username);

    List<UserEntity> findAllByUsernameContaining(String username);

    boolean existsByUsername(String username);
}