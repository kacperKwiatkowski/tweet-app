package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.mapper.UserMapper;
import com.kacperKwiatkowski.tweetApp.repository.TweetRepository;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import com.kacperKwiatkowski.tweetApp.security.auth.UserAuthService;
import com.kacperKwiatkowski.tweetApp.service.UserService;
import com.kacperKwiatkowski.tweetApp.util.UserObjectProvider;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static com.kacperKwiatkowski.tweetApp.util.MvcTestUtil.asJsonStringWithDate;
import static com.kacperKwiatkowski.tweetApp.util.MvcTestUtil.asUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)  // For Junit5
class UserControllerTest {

    private static final String USERS_URL = "/users";

    @MockBean
    private UserService userService;

    @MockBean
    private UserAuthService userAuthService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TweetRepository tweetRepository;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void shouldGetAllTheUsers() {

        mockMvc.perform(
                        asUser(get(USERS_URL + "/all")
                                .contentType(MediaType.APPLICATION_JSON))
                                .content(asJsonStringWithDate(UserObjectProvider.provideUserDto())))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }
}