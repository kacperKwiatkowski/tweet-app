package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.exception.exceptions.AvatarParseException;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class AvatarService {

    public Binary addAvatar(MultipartFile file) {
        try {
            return new Binary(BsonBinarySubType.BINARY, file.getBytes());
        } catch (IOException e) {
            throw new AvatarParseException();
        }
    }

}
