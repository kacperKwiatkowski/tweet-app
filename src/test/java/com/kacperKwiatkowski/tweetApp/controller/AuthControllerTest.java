package com.kacperKwiatkowski.tweetApp.controller;

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
import org.springframework.test.web.servlet.MockMvc;

import static com.kacperKwiatkowski.tweetApp.util.MvcTestUtil.asJsonStringWithDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @MockBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void shouldReceiveStatus201ForUserRegistration() {
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonStringWithDate(UserObjectProvider.provideUserDto())))
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus200ForForgettingPassword() {
        mockMvc.perform(get("/username/forgot"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }
}