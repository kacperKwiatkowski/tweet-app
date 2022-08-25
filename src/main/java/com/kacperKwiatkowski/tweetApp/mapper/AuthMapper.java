package com.kacperKwiatkowski.tweetApp.mapper;

import com.google.gson.Gson;
import com.kacperKwiatkowski.tweetApp.dto.auth.ForgotPasswordDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AuthMapper {

    @Autowired
    private Gson gson;

    public ForgotPasswordDto mapStringToForgottenPasswordDto(String stringToConvert) {
        return gson.fromJson(stringToConvert, ForgotPasswordDto.class);
    }
}
