package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/images")
@CrossOrigin
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PreAuthorize("hasAuthority('level:auth')")
    @GetMapping("/{username}")
    String getAllUsersByUsername(@PathVariable String username){
        return imageService.downloadImage(username);
    }
}
