package com.kacperKwiatkowski.tweetApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
}
