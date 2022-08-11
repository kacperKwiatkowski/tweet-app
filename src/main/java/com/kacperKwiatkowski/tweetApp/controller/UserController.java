package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    void getAllUsers(){

    }

    //username path variable is partial
    @GetMapping("/search/{username}")
    void getAllUsersByUsername(@PathVariable String username){

    }
}
