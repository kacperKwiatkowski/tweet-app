package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.exception.exceptions.AvatarParseException;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import com.kacperKwiatkowski.tweetApp.validator.image.ImageValidatorFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    public byte[] scaleImage(byte[] fileData, int width, int height) {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(in);
            if(height == 0) {
                height = (width * img.getHeight())/ img.getWidth();
            }
            if(width == 0) {
                width = (height * img.getWidth())/ img.getHeight();
            }
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "jpg", buffer);

            return buffer.toByteArray();
        } catch (IOException e) {
            throw new AvatarParseException("IOException in scale");
        }
    }
}
