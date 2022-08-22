package com.kacperKwiatkowski.tweetApp.mapper;

import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import com.kacperKwiatkowski.tweetApp.service.AvatarService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;

@AllArgsConstructor
@Component
public class UserMapper {

    private final AvatarService avatarService;
    private final PasswordEncoder passwordEncoder;

    private static final ModelMapper modelMapper = new ModelMapper();

    public UserDto fromEntityToUserDto(UserEntity userToConvert) {
        UserDto convertedUser = modelMapper.map(userToConvert, UserDto.class);
        convertedUser.setAvatar(Base64.getEncoder().encodeToString(userToConvert.getAvatar().getData()));
        return convertedUser;
    }

    public UserEntity fromRegisterUserDtoToEntity(RegisterUserDto userToConvert) {
        UserEntity convertedUser = modelMapper
                .map(userToConvert, UserEntity.class);

        convertedUser.setAvatar(avatarService.addAvatar(userToConvert.getAvatar()));
        convertedUser.setPassword(passwordEncoder.encode(userToConvert.getPassword()));

        return convertedUser;
    }

    public RegisterUserDto fromEntityToRegisterUserDto(UserEntity userToConvert) {
        return modelMapper
                .map(userToConvert, RegisterUserDto.class);
    }
}
