package com.kacperKwiatkowski.tweetApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    @PostMapping("/register")
    void register(){

    }

    @GetMapping("/{username}/forgot")
    void forgotPassword(@PathVariable String username){

    }
}
