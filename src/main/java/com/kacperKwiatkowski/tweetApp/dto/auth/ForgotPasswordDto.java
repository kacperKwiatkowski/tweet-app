package com.kacperKwiatkowski.tweetApp.dto.auth;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ForgotPasswordDto {

    private String username;
    private String password;
}
