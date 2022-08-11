package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
}
