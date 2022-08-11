package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('level:auth')")
    @GetMapping("/all")
    String getAllUsers(){
        return "SUCCESS";
    }

    @GetMapping("/each")
    String getAll(){
        return "SUCCESS";
    }

    //username path variable is partial
    @GetMapping("/search/{username}")
    void getAllUsersByUsername(@PathVariable String username){

    }
}
