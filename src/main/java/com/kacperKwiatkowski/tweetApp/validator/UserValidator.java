package com.kacperKwiatkowski.tweetApp.validator;

import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class UserValidator {

    private final UserRepository userRepository;

    private static final String USERNAME_NON_EXISTENT_EXCEPTION_MESSAGE = "User with following username doesn't exists in the repository: ";
    private static final String USERNAME_EXISTENT_EXCEPTION_MESSAGE = "User with following username exists in the repository: ";
    private static final String EMAIL_NON_EXISTENT_EXCEPTION_MESSAGE = "User with following email doesn't exists in the repository: ";
    private static final String EMAIL_EXISTENT_EXCEPTION_MESSAGE = "User with following email exists in the repository: ";
    private static final String PASSWORDS_MISMATCH_EXCEPTION_MESSAGE = "Given password do not match";

    public Optional<String> checkIfUserExistsByUsername(String username) {
        return !userRepository.existsByUsername(username) ? Optional.empty() : Optional.of(USERNAME_EXISTENT_EXCEPTION_MESSAGE + username);
    }

    public Optional<String> checkIfUserExistsByEmail(String email) {
        return !userRepository.existsByUsername(email) ? Optional.empty() : Optional.of(EMAIL_EXISTENT_EXCEPTION_MESSAGE + email);
    }

    public Optional<String> checkIfUserDoesntExistsByUsername(String username) {
        return userRepository.existsByUsername(username) ? Optional.empty() : Optional.of(USERNAME_NON_EXISTENT_EXCEPTION_MESSAGE + username);
    }

    public Optional<String> checkIfUserDoesntExistsByEmail(String email) {
        return userRepository.existsByUsername(email) ? Optional.empty() : Optional.of(EMAIL_NON_EXISTENT_EXCEPTION_MESSAGE + email);
    }

    public Optional<String> checkIfPasswordAndPasswordConfirmAreValid(String password, String passwordConfirm) {
        return password.equals(passwordConfirm) ? Optional.empty() : Optional.of(PASSWORDS_MISMATCH_EXCEPTION_MESSAGE);
    }
}
