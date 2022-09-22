package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import com.kacperKwiatkowski.tweetApp.validator.image.ImageValidatorFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Base64;

@Service
@CrossOrigin
@AllArgsConstructor
public class ImageService {

    private final UserRepository userRepository;
    private final ImageValidatorFacade imageValidatorFacade;

    public String downloadImage(String username) {
        imageValidatorFacade.validateImageDownload(username);

        byte[] imageBytes = userRepository.findUserEntityByUsername(username).getAvatar();

        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
