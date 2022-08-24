package com.kacperKwiatkowski.tweetApp.mapper;

import com.google.gson.Gson;
import com.kacperKwiatkowski.tweetApp.dto.auth.ForgotPasswordDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AuthMapper {

    private static final Gson jsonMapper = new Gson();

    public ForgotPasswordDto mapStringToForgottenPasswordDto(String stringToConvert) {
        return jsonMapper.fromJson(stringToConvert, ForgotPasswordDto.class);
    }
}
