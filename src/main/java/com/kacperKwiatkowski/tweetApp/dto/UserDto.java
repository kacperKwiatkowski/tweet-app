package com.kacperKwiatkowski.tweetApp.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
}
