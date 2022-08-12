package com.kacperKwiatkowski.tweetApp.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthDto {

    private String username;
    private String password;
}
