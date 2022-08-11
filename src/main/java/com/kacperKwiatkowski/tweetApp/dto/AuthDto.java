package com.kacperKwiatkowski.tweetApp.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthDto {

    private String username;
    private String password;
}
