package com.kacperKwiatkowski.tweetApp.mapper;

import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

@AllArgsConstructor
@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public UserDto fromEntityToUserDto(UserEntity userToConvert) {
        UserDto convertedUser = modelMapper.map(userToConvert, UserDto.class);
        convertedUser.setAvatar(Base64.getEncoder().encodeToString(userToConvert.getAvatar()));
        return convertedUser;
    }

    public UserEntity fromRegisterUserDtoToEntity(RegisterUserDto userToConvert) {
        UserEntity convertedUser = modelMapper
                .map(userToConvert, UserEntity.class);

        try {
            convertedUser.setAvatar(userToConvert.getAvatar().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        convertedUser.setPassword(passwordEncoder.encode(userToConvert.getPassword()));

        return convertedUser;
    }

    public RegisterUserDto fromEntityToRegisterUserDto(UserEntity userToConvert) {
        return modelMapper
                .map(userToConvert, RegisterUserDto.class);
    }
}
