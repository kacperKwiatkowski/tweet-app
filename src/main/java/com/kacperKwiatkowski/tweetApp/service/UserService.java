package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.mapper.UserMapper;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .toList()
                .stream()
                .map(userMapper::fromEntityToUserDto)
                .toList();
    }

    public List<UserDto> getAllUsersByUsername(String username) {
        return userRepository.findAllByUsernameContaining(username).stream()
                .map(userMapper::fromEntityToUserDto)
                .toList();
    }
}
