
package com.kacperKwiatkowski.tweetApp.mapper;

import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import com.kacperKwiatkowski.tweetApp.util.UserObjectProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserMapper userMapper;

    @Test
    void shouldMapUserEntityToUserDto() {
        // given
        UserEntity userToMap = UserObjectProvider.provideUserEntity();

        // when
        UserDto mappedUser = userMapper.fromEntityToUserDto(userToMap);

        // then
        assertEquals(userToMap.getId(), mappedUser.getId());
        assertEquals(userToMap.getFirstName(), mappedUser.getFirstName());
        assertEquals(userToMap.getLastName(), mappedUser.getLastName());
        assertEquals(userToMap.getEmail(), mappedUser.getEmail());
        assertEquals(userToMap.getUsername(), mappedUser.getUsername());
    }

    @Test
    void shouldMapRegisterUserDtoToEntity() {
        // given
        RegisterUserDto userToMap = UserObjectProvider.provideRegisterUserDto();
        when(passwordEncoder.encode(any())).thenReturn(userToMap.getPassword());

        // when
        UserEntity mappedUser = userMapper.fromRegisterUserDtoToEntity(userToMap);

        // then
        assertEquals(userToMap.getId(), mappedUser.getId());
        assertEquals(userToMap.getFirstName(), mappedUser.getFirstName());
        assertEquals(userToMap.getLastName(), mappedUser.getLastName());
        assertEquals(userToMap.getEmail(), mappedUser.getEmail());
        assertEquals(userToMap.getUsername(), mappedUser.getUsername());
        assertEquals(userToMap.getAvatar(), mappedUser.getAvatar());
        assertEquals(userToMap.getPassword(), mappedUser.getPassword());
        assertEquals(userToMap.getRoleType(), mappedUser.getRoleType());
    }

}