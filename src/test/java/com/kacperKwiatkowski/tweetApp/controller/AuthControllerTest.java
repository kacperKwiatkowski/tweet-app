package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.service.AuthService;
import com.kacperKwiatkowski.tweetApp.util.UserObjectProvider;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.FileInputStream;
import java.util.HashMap;

import static com.kacperKwiatkowski.tweetApp.util.MvcTestUtil.asJsonStringWithDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    MockMultipartFile MOCK_AVATAR =
            new MockMultipartFile(
                    "mock.tmp",
                    "mock.tmp",
                    MediaType.MULTIPART_FORM_DATA_VALUE,
                    "MOCK".getBytes()
            );

    @MockBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void shouldReceiveStatus201ForUserRegistration() {
        RegisterUserDto rud = UserObjectProvider.provideRegisterUserDto();

        RequestBuilder request =
                multipart("/register")
                        .file("avatar", rud.getAvatar().getBytes())
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .param("firstName", rud.getFirstName())
                        .param("lastName", rud.getLastName())
                        .param("contactNumber", rud.getContactNumber())
                        .param("email", rud.getEmail())
                        .param("username", rud.getUsername())
                        .param("password", rud.getPassword())
                        .param("passwordConfirm", rud.getPasswordConfirm());

        mockMvc
                .perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus200ForForgettingPassword() {
        mockMvc.perform(get("/username/forgot"))
                .andExpect(status().isOk());
    }
}