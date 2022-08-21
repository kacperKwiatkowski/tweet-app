package com.kacperKwiatkowski.tweetApp.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterUserDto {

    @NotEmpty(message = "Can't be empty")
    private String firstName;

    @NotEmpty(message = "Can't be empty")
    private String lastName;

    @NotEmpty(message = "Can't be empty")
    private String contactNumber;

    @Email(message = "Must be in email format")
    private String email;

    @NotEmpty(message = "Can't be empty")
    private String username;

    @NotEmpty(message = "Can't be empty")
    private String avatar;

    @NotEmpty(message = "Can't be empty")
    private String password;

    @NotEmpty(message = "Can't be empty")
    private String passwordConfirm;
}
