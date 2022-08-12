package com.kacperKwiatkowski.tweetApp.mapper;

import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public UserDto toDto(UserEntity userToConvert){
        return modelMapper
                .map(userToConvert, UserDto.class);
    }
}
