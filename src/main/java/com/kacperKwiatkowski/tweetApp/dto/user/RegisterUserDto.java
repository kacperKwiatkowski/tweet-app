package com.kacperKwiatkowski.tweetApp.dto.user;

import com.kacperKwiatkowski.tweetApp.security.role.RoleType;
import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterUserDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String avatar;
    private String password;
    private String passwordConfirm;
    private RoleType roleType;


}
