package com.kacperKwiatkowski.tweetApp.util;

import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import com.kacperKwiatkowski.tweetApp.security.role.RoleType;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.UUID;

public class UserObjectProvider {

    public static final MockMultipartFile MOCK_AVATAR = new MockMultipartFile(
            "mock.png",
            "mock.png",
            MediaType.MULTIPART_FORM_DATA_VALUE,
            "MOCK".getBytes()
    );
    public static final String EMAIL_SUFFIX = "@gmail";

    @SneakyThrows
    public static UserEntity provideUserEntity() {

        String randomFieldIndex = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        return UserEntity.builder()
                .id(UUID.randomUUID())
                .firstName(randomFieldIndex)
                .lastName(randomFieldIndex)
                .contactNumber(randomFieldIndex)
                .email(randomFieldIndex + EMAIL_SUFFIX)
                .username(randomFieldIndex)
                .avatar(MOCK_AVATAR.getBytes())
                .password(randomFieldIndex)
                .roleType(RoleType.USER.name())
                .build();
    }

    public static UserDto provideUserDto() {

        String randomFieldIndex = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        return UserDto.builder()
                .id(UUID.randomUUID())
                .firstName(randomFieldIndex)
                .lastName(randomFieldIndex)
                .email(randomFieldIndex + EMAIL_SUFFIX)
                .username(randomFieldIndex)
                .avatar(randomFieldIndex)
                .build();
    }

    public static RegisterUserDto provideRegisterUserDto() {

        String randomFieldIndex = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        return RegisterUserDto.builder()
                .firstName(randomFieldIndex)
                .lastName(randomFieldIndex)
                .contactNumber(randomFieldIndex)
                .email(randomFieldIndex + EMAIL_SUFFIX)
                .username(randomFieldIndex)
                .avatar(MOCK_AVATAR)
                .password(randomFieldIndex)
                .passwordConfirm(randomFieldIndex)
                .build();
    }
}
