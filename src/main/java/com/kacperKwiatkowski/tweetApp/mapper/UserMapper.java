package com.kacperKwiatkowski.tweetApp.mapper;

import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    private static final ModelMapper modelMapper = new ModelMapper();

    public UserDto fromEntityToUserDto(UserEntity userToConvert) {
        return modelMapper
                .map(userToConvert, UserDto.class);
    }

    public UserEntity fromRegisterUserDtoToEntity(RegisterUserDto userToConvert) {
        UserEntity convertedUser = modelMapper
                .map(userToConvert, UserEntity.class);

        convertedUser.setPassword(passwordEncoder.encode(userToConvert.getPassword()));

        return convertedUser;
    }

    public RegisterUserDto fromEntityToRegisterUserDto(UserEntity userToConvert) {
        return modelMapper
                .map(userToConvert, RegisterUserDto.class);
    }
}
