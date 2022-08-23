package com.kacperKwiatkowski.tweetApp.dto.user;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile avatar;

    @NotEmpty(message = "Can't be empty")
    private String password;

    @NotEmpty(message = "Can't be empty")
    private String passwordConfirm;
}
