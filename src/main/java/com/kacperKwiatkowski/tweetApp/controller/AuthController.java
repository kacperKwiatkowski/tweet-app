package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    UserDto register(@RequestBody RegisterUserDto userToRegister) {
        return authService.registerUser(userToRegister);
    }

    @GetMapping("/{username}/forgot")
    void forgotPassword(@PathVariable String username) {

    }
}
