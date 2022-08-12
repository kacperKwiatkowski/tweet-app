package com.kacperKwiatkowski.tweetApp.validator;

import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public record UserValidator(
        UserRepository userRepository
) {

    private static final String USER_NON_EXISTENT_EXCEPTION_MESSAGE = "Following user doesn't exists in the repository: ";

    public Optional<String> checkIfUserExists(String username) {
        return userRepository.existsByUsername(username) ? Optional.empty() : Optional.of(USER_NON_EXISTENT_EXCEPTION_MESSAGE + username);
    }
}
