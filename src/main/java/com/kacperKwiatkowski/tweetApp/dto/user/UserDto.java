package com.kacperKwiatkowski.tweetApp.dto.user;

import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String avatar;
}
