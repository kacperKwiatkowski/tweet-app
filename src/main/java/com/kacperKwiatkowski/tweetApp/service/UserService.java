package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.mapper.UserMapper;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        log.info("Service 'UserService::getAllUsers' invoked.");

        return userRepository.findAll().stream()
                .map(userMapper::fromEntityToUserDto)
                .toList();
    }

    public List<UserDto> getAllUsersByUsername(String username) {
        log.info("Service 'UserService::getAllUsersByUsername' invoked.");
        return userRepository.findAllByUsernameContaining(username).stream()
                .map(userMapper::fromEntityToUserDto)
                .toList();
    }
}
