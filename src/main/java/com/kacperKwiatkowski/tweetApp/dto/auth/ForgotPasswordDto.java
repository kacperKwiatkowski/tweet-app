package com.kacperKwiatkowski.tweetApp.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ForgotPasswordDto {

    private String password;
    private String passwordConfirm;
}
