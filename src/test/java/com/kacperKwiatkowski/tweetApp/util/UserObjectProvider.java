package com.kacperKwiatkowski.tweetApp.util;

import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import com.kacperKwiatkowski.tweetApp.security.role.RoleType;

import java.util.UUID;

public class UserObjectProvider {

    public static UserEntity provideUserEntity() {

        String randomFieldIndex = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        return UserEntity.builder()
                .id(UUID.randomUUID())
                .firstName(randomFieldIndex)
                .lastName(randomFieldIndex)
                .email(randomFieldIndex)
                .username(randomFieldIndex)
                .avatar(randomFieldIndex)
                .password(randomFieldIndex)
                .roleType(RoleType.USER)
                .build();
    }

    public static UserDto provideUserDto() {

        String randomFieldIndex = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        return UserDto.builder()
                .id(UUID.randomUUID())
                .firstName(randomFieldIndex)
                .lastName(randomFieldIndex)
                .email(randomFieldIndex)
                .username(randomFieldIndex)
                .build();
    }

    public static RegisterUserDto provideRegisterUserDto() {

        String randomFieldIndex = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        return RegisterUserDto.builder()
                .id(UUID.randomUUID())
                .firstName(randomFieldIndex)
                .lastName(randomFieldIndex)
                .email(randomFieldIndex)
                .username(randomFieldIndex)
                .avatar(randomFieldIndex)
                .password(randomFieldIndex)
                .passwordConfirm(randomFieldIndex)
                .roleType(RoleType.USER)
                .build();
    }
}
