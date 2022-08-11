package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    String getAllUsers(){
        return "SUCCESS";
    }

    //username path variable is partial
    @GetMapping("/search/{username}")
    void getAllUsersByUsername(@PathVariable String username){

    }
}
