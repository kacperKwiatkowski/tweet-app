package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.service.TweetService;
import com.kacperKwiatkowski.tweetApp.util.JwtTokenProvider;
import com.kacperKwiatkowski.tweetApp.util.TweetObjectProvider;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.kacperKwiatkowski.tweetApp.util.MvcTestUtil.asJsonStringWithDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TweetControllerTest {

    @MockBean
    private TweetService tweetService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @SneakyThrows
    @Test
    void shouldReceiveStatus200ForGetAllTweets() {
        mockMvc.perform(get("/all")
                        .header("Authorization", "Bearer " + jwtTokenProvider.provideToken()))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus403ForGetAllTweets() {
        mockMvc.perform(get("/all"))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus200ForGetAllTweetsByUsername() {
        mockMvc.perform(get("/username")
                        .header("Authorization", "Bearer " + jwtTokenProvider.provideToken()))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus403ForGetAllTweetsByUsername() {
        mockMvc.perform(get("/username"))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus200ForSaveTweet() {
        mockMvc.perform(post("/username/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonStringWithDate(TweetObjectProvider.provideCreateTweetDto()))
                        .header("Authorization", "Bearer " + jwtTokenProvider.provideToken()))
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus403ForSaveTweet() {
        mockMvc.perform(post("/username/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonStringWithDate(TweetObjectProvider.provideCreateTweetDto())))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus200ForUpdateTweet() {
        mockMvc.perform(put("/username/update/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonStringWithDate(TweetObjectProvider.provideUpdateTweetDto()))
                        .header("Authorization", "Bearer " + jwtTokenProvider.provideToken()))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus403ForUpdateTweet() {
        mockMvc.perform(put("/username/update/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonStringWithDate(TweetObjectProvider.provideUpdateTweetDto())))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus200ForDeleteTweet() {
        mockMvc.perform(delete("/username/delete/" + UUID.randomUUID())
                        .header("Authorization", "Bearer " + jwtTokenProvider.provideToken()))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus403ForDeleteTweet() {
        mockMvc.perform(delete("/username/delete" + UUID.randomUUID()))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus200ForLikeTweet() {
        mockMvc.perform(put("/username/like/" + UUID.randomUUID())
                        .header("Authorization", "Bearer " + jwtTokenProvider.provideToken()))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus403ForLikeTweet() {
        mockMvc.perform(put("/username/like/" + UUID.randomUUID()))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus200ForReplyToTweet() {
        mockMvc.perform(post("/username/reply/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonStringWithDate(TweetObjectProvider.provideReplyTweetDto()))
                        .header("Authorization", "Bearer " + jwtTokenProvider.provideToken()))
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @SneakyThrows
    @Test
    void shouldReceiveStatus403ForReplyToTweet() {
        mockMvc.perform(post("/username/reply/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonStringWithDate(TweetObjectProvider.provideReplyTweetDto())))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }
}