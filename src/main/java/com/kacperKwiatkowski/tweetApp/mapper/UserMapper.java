package com.kacperKwiatkowski.tweetApp.mapper;

import com.kacperKwiatkowski.tweetApp.dto.UserDto;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
