package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('level:auth')")
    @GetMapping("/all")
    List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    //username path variable is partial
    @PreAuthorize("hasAuthority('level:auth')")
    @GetMapping("/search/{username}")
    List<UserDto> getAllUsersByUsername(@PathVariable String username){
        return userService.getAllUsersByUsername(username);
    }
}
