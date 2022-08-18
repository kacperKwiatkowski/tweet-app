package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.dto.tweet.CreateTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.PersistedTweetDto;
import com.kacperKwiatkowski.tweetApp.dto.tweet.UpdateTweetDto;
import com.kacperKwiatkowski.tweetApp.model.TweetEntity;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import com.kacperKwiatkowski.tweetApp.repository.TweetRepository;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import com.kacperKwiatkowski.tweetApp.util.JwtTokenProvider;
import com.kacperKwiatkowski.tweetApp.util.TweetObjectProvider;
import com.kacperKwiatkowski.tweetApp.util.UserObjectProvider;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TweetControllerIntegrationTest {

    private static final String MATCHING_USERNAME_ONE = "USERNAME_ONE";
    private static final String MATCHING_USERNAME_TWO = "USERNAME_TWO";
    private static final String NOT_MATCHING_USERNAME = "NOT_MATCHING";
    private static final String USERNAME = "USERNAME";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private WebTestClient webTestClient;

    @AfterAll
    void cleanUp() {
        userRepository.deleteAll();
        tweetRepository.deleteAll();
    }

    @Test
    @Order(1)
    void shouldGetAllTweetsWith200StatusCode() throws ServletException, IOException {
        // given
        UserEntity persistedUser = userRepository.save(UserObjectProvider.provideUserEntity());
        TweetEntity persistedTweet = TweetObjectProvider.provideTweetEntity();
        persistedTweet.setUsername(persistedUser.getUsername());
        tweetRepository.save(persistedTweet);

        // when
        EntityExchangeResult<List<PersistedTweetDto>> result = webTestClient.get()
                .uri("/all")
                .header("Authorization", "Bearer " + jwtTokenProvider.provideToken())
                .exchange()
                .expectBodyList(PersistedTweetDto.class)
                .returnResult();

        // then
        assertNotNull(result);
        assertNotNull(result.getResponseBody());
        assertEquals(HttpStatus.OK, result.getStatus());
        assertTrue(comparePersistedTweetEntityToResponseBody(persistedTweet, result.getResponseBody()));
    }

    @Test
    @Order(2)
    void shouldGetTweetsWithMatchingUsernameWith200StatusCode() throws ServletException, IOException {
        // given
        UserEntity matchingUsernameUserOne = UserObjectProvider.provideUserEntity();
        matchingUsernameUserOne.setUsername(MATCHING_USERNAME_ONE);
        UserEntity matchingUsernameUserTwo = UserObjectProvider.provideUserEntity();
        matchingUsernameUserTwo.setUsername(MATCHING_USERNAME_TWO);
        List<UserEntity> matchingUsers = List.of(matchingUsernameUserOne, matchingUsernameUserTwo);

        UserEntity notMatchingUsernameUser = UserObjectProvider.provideUserEntity();
        notMatchingUsernameUser.setUsername(NOT_MATCHING_USERNAME);
        List<UserEntity> nonMatchingUsers = List.of(notMatchingUsernameUser);

        TweetEntity matchingTweetOne = TweetObjectProvider.provideTweetEntity();
        matchingTweetOne.setUsername(MATCHING_USERNAME_ONE);
        TweetEntity matchingTweetTwo = TweetObjectProvider.provideTweetEntity();
        matchingTweetTwo.setUsername(MATCHING_USERNAME_ONE);
        TweetEntity matchingTweetThree = TweetObjectProvider.provideTweetEntity();
        matchingTweetThree.setUsername(MATCHING_USERNAME_TWO);
        TweetEntity matchingTweetFour = TweetObjectProvider.provideTweetEntity();
        matchingTweetFour.setUsername(MATCHING_USERNAME_TWO);
        List<TweetEntity> matchingTweets = List.of(matchingTweetOne, matchingTweetTwo, matchingTweetThree, matchingTweetFour);

        TweetEntity nonMatchingTweetOne = TweetObjectProvider.provideTweetEntity();
        nonMatchingTweetOne.setUsername(NOT_MATCHING_USERNAME);
        TweetEntity nonMatchingTweetTwo = TweetObjectProvider.provideTweetEntity();
        nonMatchingTweetTwo.setUsername(NOT_MATCHING_USERNAME);
        List<TweetEntity> nonMatchingTweets = List.of(nonMatchingTweetOne, nonMatchingTweetTwo);

        userRepository.saveAll(Stream.concat(matchingUsers.stream(), nonMatchingUsers.stream()).toList());
        tweetRepository.saveAll(Stream.concat(matchingTweets.stream(), nonMatchingTweets.stream()).toList());

        // when
        EntityExchangeResult<List<PersistedTweetDto>> result = webTestClient.get()
                .uri("/" + USERNAME)
                .header("Authorization", "Bearer " + jwtTokenProvider.provideToken())
                .exchange()
                .expectBodyList(PersistedTweetDto.class)
                .returnResult();

        // then
        assertNotNull(result);
        assertNotNull(result.getResponseBody());
        assertEquals(HttpStatus.OK, result.getStatus());
        assertEquals(matchingTweets.size(), result.getResponseBody().size());
        assertTrue(matchingTweets.stream().allMatch(matchingTweet -> comparePersistedTweetEntityToResponseBody(matchingTweet, result.getResponseBody())));
    }

    @Test
    @Order(3)
    void shouldCreateTweetWith201StatusCode() throws ServletException, IOException {
        // given
        CreateTweetDto tweetToCreate = TweetObjectProvider.provideCreateTweetDto();
        UserEntity tweetOwner = UserObjectProvider.provideUserEntity();
        tweetOwner.setUsername(USERNAME);
        userRepository.save(tweetOwner);

        // when
        EntityExchangeResult<PersistedTweetDto> result = webTestClient.post()
                .uri("/" + USERNAME + "/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(tweetToCreate)
                .header("Authorization", "Bearer " + jwtTokenProvider.provideToken())
                .exchange()
                .expectBody(PersistedTweetDto.class)
                .returnResult();

        // then
        assertNotNull(result);
        assertNotNull(result.getResponseBody());
        assertEquals(HttpStatus.CREATED, result.getStatus());

        Optional<TweetEntity> persistedTweet = tweetRepository.findById(result.getResponseBody().getTweetId());

        assertTrue(persistedTweet.isPresent());
        assertEquals(tweetToCreate.getTitle(), result.getResponseBody().getTitle(), persistedTweet.get().getTitle());
        assertEquals(tweetToCreate.getMessage(), result.getResponseBody().getMessage(), persistedTweet.get().getMessage());
//        assertEquals(tweetToCreate.getPostDateTime(), result.getResponseBody().getPostDateTime(), persistedTweet.get().getPostDateTime());
    }

    @Test
    @Order(4)
    void shouldUpdateTweetWith200StatusCode() throws ServletException, IOException {
        // given
        UpdateTweetDto tweetUpdateDto = TweetObjectProvider.provideUpdateTweetDto();

        UserEntity tweetOwner = UserObjectProvider.provideUserEntity();
        tweetOwner.setUsername(USERNAME);
        userRepository.save(tweetOwner);

        TweetEntity updatableTweet = TweetObjectProvider.provideTweetEntity();
        updatableTweet.setUsername(USERNAME);
        tweetRepository.save(updatableTweet);

        // when
        EntityExchangeResult<PersistedTweetDto> result = webTestClient.put()
                .uri("/" + USERNAME + "/update/" + updatableTweet.getTweetId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(tweetUpdateDto)
                .header("Authorization", "Bearer " + jwtTokenProvider.provideToken())
                .exchange()
                .expectBody(PersistedTweetDto.class)
                .returnResult();

        // then
        assertNotNull(result);
        assertNotNull(result.getResponseBody());
        assertEquals(HttpStatus.OK, result.getStatus());

        Optional<TweetEntity> persistedTweet = tweetRepository.findById(result.getResponseBody().getTweetId());

        assertTrue(persistedTweet.isPresent());
        assertEquals(tweetUpdateDto.getTitle(), result.getResponseBody().getTitle(), persistedTweet.get().getTitle());
        assertEquals(tweetUpdateDto.getMessage(), result.getResponseBody().getMessage(), persistedTweet.get().getMessage());
//        assertEquals(tweetToCreate.getPostDateTime(), result.getResponseBody().getPostDateTime(), persistedTweet.get().getPostDateTime());
    }

    @Test
    @Order(5)
    void shouldDeleteTweetWith200StatusCode() throws ServletException, IOException {
        // given
        UserEntity tweetOwner = UserObjectProvider.provideUserEntity();
        tweetOwner.setUsername(USERNAME);
        userRepository.save(tweetOwner);

        TweetEntity removableTweet = TweetObjectProvider.provideTweetEntity();
        removableTweet.setUsername(USERNAME);
        tweetRepository.save(removableTweet);

        // when
        webTestClient.delete()
                .uri("/" + USERNAME + "/delete/" + removableTweet.getTweetId())
                .header("Authorization", "Bearer " + jwtTokenProvider.provideToken())
                .exchange();

        // then
        assertFalse(tweetRepository.existsById(removableTweet.getTweetId()));
    }

    @Test
    @Order(6)
    void shouldIncreaseTweetLikeCountWith200StatusCode() throws ServletException, IOException {
        // given
        UserEntity tweetOwner = UserObjectProvider.provideUserEntity();
        tweetOwner.setUsername(USERNAME);
        userRepository.save(tweetOwner);

        TweetEntity tweetToIncreaseLikeCount = TweetObjectProvider.provideTweetEntity();
        tweetToIncreaseLikeCount.setUsername(USERNAME);
        tweetToIncreaseLikeCount.setLikeCount(0);
        tweetRepository.save(tweetToIncreaseLikeCount);

        // when
        EntityExchangeResult<PersistedTweetDto> result = webTestClient.put()
                .uri("/" + USERNAME + "/like/" + tweetToIncreaseLikeCount.getTweetId())
                .header("Authorization", "Bearer " + jwtTokenProvider.provideToken())
                .exchange()
                .expectBody(PersistedTweetDto.class)
                .returnResult();

        // then
        Optional<TweetEntity> tweetFromRepository = tweetRepository.findById(tweetToIncreaseLikeCount.getTweetId());
        assertNotNull(result);
        assertNotNull(result.getResponseBody());
        assertTrue(tweetFromRepository.isPresent());
        assertEquals(tweetFromRepository.get().getTweetId(), result.getResponseBody().getTweetId());
        assertNotEquals(tweetToIncreaseLikeCount.getLikeCount(), result.getResponseBody().getLikeCount());
        assertNotEquals(tweetToIncreaseLikeCount.getLikeCount(), tweetFromRepository.get().getLikeCount());
        assertEquals(result.getResponseBody().getLikeCount(), tweetFromRepository.get().getLikeCount());
    }

    @Test
    @Order(7)
    void shouldReplyToTweetWith200StatusCode() throws ServletException, IOException {
        // given
        UserEntity tweetOwner = UserObjectProvider.provideUserEntity();
        tweetOwner.setUsername(USERNAME);
        userRepository.save(tweetOwner);

        TweetEntity originalTweet = TweetObjectProvider.provideTweetEntity();
        originalTweet.setUsername(USERNAME);
        tweetRepository.save(originalTweet);

        TweetEntity replyTweet = TweetObjectProvider.provideTweetEntity();
        replyTweet.setThreadId(originalTweet.getThreadId());
        replyTweet.setUsername(USERNAME);

        // when
        EntityExchangeResult<PersistedTweetDto> result = webTestClient.post()
                .uri("/" + USERNAME + "/reply/" + originalTweet.getTweetId())
                .header("Authorization", "Bearer " + jwtTokenProvider.provideToken())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(replyTweet)
                .exchange()
                .expectBody(PersistedTweetDto.class)
                .returnResult();

        // then
        assertNotNull(result);
        assertNotNull(result.getResponseBody());
        Optional<TweetEntity> tweetFromRepository = tweetRepository.findById(result.getResponseBody().getTweetId());
        assertTrue(tweetFromRepository.isPresent());
        assertEquals(tweetFromRepository.get().getTweetId(), result.getResponseBody().getTweetId());
        assertEquals(originalTweet.getThreadId().toString(), tweetFromRepository.get().getThreadId().toString());
        assertEquals(replyTweet.getThreadId().toString(), tweetFromRepository.get().getThreadId().toString());
    }

    private boolean comparePersistedTweetEntityToResponseBody(TweetEntity persistedTweet, List<PersistedTweetDto> result) {
        return result.stream()
                .anyMatch(tweet ->
                        tweet.getUsername().equals(persistedTweet.getUsername()) &&
                                tweet.getTweetId().equals(persistedTweet.getTweetId()) &&
                                tweet.getTitle().equals(persistedTweet.getTitle()) &&
                                tweet.getMessage().equals(persistedTweet.getMessage()) &&
                                // TODO Adapt common DateTime format
                                //tweet.getPostDateTime().equals(persistedTweet.getPostDateTime()) &&
                                tweet.getLikeCount().equals(persistedTweet.getLikeCount())
                );
    }
}