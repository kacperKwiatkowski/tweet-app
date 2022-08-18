package com.kacperKwiatkowski.tweetApp.exception.advisors;

import com.kacperKwiatkowski.tweetApp.dto.user.RegisterUserDto;
import com.kacperKwiatkowski.tweetApp.mapper.UserMapper;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import com.kacperKwiatkowski.tweetApp.repository.TweetRepository;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import com.kacperKwiatkowski.tweetApp.util.JwtTokenProvider;
import com.kacperKwiatkowski.tweetApp.util.TweetObjectProvider;
import com.kacperKwiatkowski.tweetApp.util.UserObjectProvider;
import com.kacperKwiatkowski.tweetApp.validator.ValidationReport;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControllerAdvisorIntegrationTest {

    private static final String TWEET_VALIDATION_FAILURE_MESSAGE = "Tweet validation failed";
    private static final String USER_VALIDATION_FAILURE_MESSAGE = "User validation failed";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserMapper userMapper;

    @AfterAll
    void cleanUp() {
        userRepository.deleteAll();
        tweetRepository.deleteAll();
    }

    @Test
    @Order(1)
    void shouldHandleValidationResultForUsersByControllerAdvisorAndReturn400Status() throws ServletException, IOException {
        // given
        UserEntity persistedUser = userRepository.save(UserObjectProvider.provideUserEntity());

        RegisterUserDto duplicatedUser = userMapper.fromEntityToRegisterUserDto(persistedUser);
        duplicatedUser.setPasswordConfirm(UUID.randomUUID().toString());

        // when
        EntityExchangeResult<ValidationReport> result = webTestClient.post()
                .uri("/register")
                .header("Authorization", "Bearer " + jwtTokenProvider.provideToken())
                .bodyValue(duplicatedUser)
                .exchange()
                .expectBody(ValidationReport.class)
                .returnResult();

        // then
        assertNotNull(result);
        assertNotNull(result.getResponseBody());
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatus());
        assertEquals(USER_VALIDATION_FAILURE_MESSAGE, result.getResponseBody().validationFailureMessage);
    }

    @Test
    @Order(2)
    void shouldHandleValidationResultForTweetsByControllerAdvisorAndReturn400Status() throws ServletException, IOException {
        // given
        UserEntity notPersistedUser = UserObjectProvider.provideUserEntity();

        // when
        EntityExchangeResult<ValidationReport> result = webTestClient.post()
                .uri("/" + notPersistedUser.getUsername() + "/add")
                .header("Authorization", "Bearer " + jwtTokenProvider.provideToken())
                .bodyValue(TweetObjectProvider.provideCreateTweetDto())
                .exchange()
                .expectBody(ValidationReport.class)
                .returnResult();

        // then
        assertNotNull(result);
        assertNotNull(result.getResponseBody());
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatus());
        assertEquals(TWEET_VALIDATION_FAILURE_MESSAGE, result.getResponseBody().validationFailureMessage);
    }
}