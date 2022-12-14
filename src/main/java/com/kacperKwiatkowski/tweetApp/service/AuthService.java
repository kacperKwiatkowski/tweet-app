package com.kacperKwiatkowski.tweetApp.service;

import com.google.gson.Gson;
import com.kacperKwiatkowski.tweetApp.dto.auth.ForgotPasswordDto;
import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.mapper.UserMapper;
import com.kacperKwiatkowski.tweetApp.message.publisher.SqsPublisher;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import com.kacperKwiatkowski.tweetApp.validator.user.UserValidatorFacade;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthService {

    private static final String TOPIC_FORGOTTEN_PASSWORD = "user.password.forgotten";

    private final UserRepository userRepository;
    private final UserValidatorFacade userValidatorFacade;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final SqsPublisher sqsMessageProducer;

public UserDto registerUser(RegisterUserDto userToRegister) {
    userValidatorFacade.validateUserRegisterAction(userToRegister);

    return userMapper.fromEntityToUserDto(
            userRepository.save(
                    userMapper.fromRegisterUserDtoToEntity(userToRegister)
                            .assignRole()
            )
    );
    }

    public void forgotPassword(String username) {
        userValidatorFacade.validateUserForgotPassword(username);

        String tempPassword = UUID.randomUUID().toString();

        UserEntity userToRemindPassword = userRepository.findUserEntityByUsername(username);
        userToRemindPassword.setPassword(passwordEncoder.encode(tempPassword));
        userRepository.save(userToRemindPassword);

        sqsMessageProducer.send(new Gson().toJson(new ForgotPasswordDto(username, tempPassword)));
    }

    public UserDto getLoggedInUser() {
        //TODO 500 is thrown when user is not logged
        return userMapper.fromEntityToUserDto(
                userRepository.findUserEntityByUsername(
                        SecurityContextHolder.getContext().getAuthentication().getName()
                )
        );
    }
}
