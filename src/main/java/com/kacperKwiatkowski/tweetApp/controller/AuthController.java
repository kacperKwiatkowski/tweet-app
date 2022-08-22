package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@AllArgsConstructor
class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UserDto register(@Valid @ModelAttribute RegisterUserDto userToRegister) {
        return authService.registerUser(userToRegister);
    }

    @GetMapping("/{username}/forgot")
    void forgotPassword(@PathVariable String username) {

    }

    @GetMapping("/logged")
    UserDto getLoggedUser() {
        return authService.getLoggedInUser();
    }
}