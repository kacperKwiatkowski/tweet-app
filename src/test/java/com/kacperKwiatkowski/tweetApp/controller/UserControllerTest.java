package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.service.UserService;
import com.kacperKwiatkowski.tweetApp.util.JwtTokenProvider;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private static final String USERS_URL = "/users";

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @SneakyThrows
    @Test
    void shouldReceiveStatus200ForGetAllTheUsers() {
        mockMvc.perform(get(USERS_URL + "/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonStringWithDate(UserObjectProvider.provideUserDto()))
                        .header("Authorization", "Bearer " + jwtTokenProvider.provideToken()))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus403GetAllTheUsers() {
        mockMvc.perform(get(USERS_URL + "/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonStringWithDate(UserObjectProvider.provideUserDto())))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus200ForGetAllUsersByUsername() {
        mockMvc.perform(get(USERS_URL + "/search/username")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonStringWithDate(UserObjectProvider.provideUserDto()))
                        .header("Authorization", "Bearer " + jwtTokenProvider.provideToken()))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus403GetAllUsersByUsername() {
        mockMvc.perform(get(USERS_URL + "/search/username")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonStringWithDate(UserObjectProvider.provideUserDto())))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }
}