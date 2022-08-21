package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.mapper.UserMapper;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import com.kacperKwiatkowski.tweetApp.validator.UserValidatorFacade;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserValidatorFacade userValidatorFacade;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto registerUser(RegisterUserDto userToRegister) {
        userValidatorFacade.validateUserRegisterAction(userToRegister);

        return userMapper.fromEntityToUserDto(
                userRepository.save(
                        userMapper.fromRegisterUserDtoToEntity(userToRegister)
                )
        );
    }

    public UserDto getLoggedInUser() {
        return userMapper.fromEntityToUserDto(
                userRepository.findUserEntityByUsername(
                        SecurityContextHolder.getContext().getAuthentication().getName()
                )
        );
    }
}
