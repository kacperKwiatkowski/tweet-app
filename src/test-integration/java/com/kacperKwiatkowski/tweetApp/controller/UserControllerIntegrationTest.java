package com.kacperKwiatkowski.tweetApp.controller;

import com.kacperKwiatkowski.tweetApp.dto.user.UserDto;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import com.kacperKwiatkowski.tweetApp.util.JwtTokenProvider;
import com.kacperKwiatkowski.tweetApp.util.UserObjectProvider;
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
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerIntegrationTest {

    private static final String USERS_URL = "/users";

    private static final String MATCHING_USERNAME_ONE = "USERNAME_ONE";
    private static final String MATCHING_USERNAME_TWO = "USERNAME_TWO";
    private static final String NOT_MATCHING_USERNAME = "NOT_MATCHING";
    private static final String USERNAME_TO_MATCH = "USERNAME";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private WebTestClient webTestClient;

    @AfterAll
    void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    @Order(1)
    void shouldGetAllUsersWith200StatusCode() throws ServletException, IOException {
        // given
        UserEntity persistedUser = userRepository.save(UserObjectProvider.provideUserEntity());

        // when
        EntityExchangeResult<List<UserDto>> result = webTestClient.get()
                .uri(USERS_URL + "/all")
                .header("Authorization", "Bearer " + jwtTokenProvider.provideToken())
                .exchange()
                .expectBodyList(UserDto.class)
                .returnResult();

        // then
        assertNotNull(result);
        assertNotNull(result.getResponseBody());
        assertEquals(HttpStatus.OK, result.getStatus());
        assertTrue(comparePersistedUserEntityToResponseBody(persistedUser, result.getResponseBody()));
    }

    @Test
    @Order(2)
    void shouldGetUsersWithMatchingUsernameWith200StatusCode() throws ServletException, IOException {
        // given
        UserEntity matchingUsernameUserOne = UserObjectProvider.provideUserEntity();
        matchingUsernameUserOne.setUsername(MATCHING_USERNAME_ONE);
        UserEntity matchingUsernameUserTwo = UserObjectProvider.provideUserEntity();
        matchingUsernameUserTwo.setUsername(MATCHING_USERNAME_TWO);
        List<UserEntity> matchingUsers = List.of(matchingUsernameUserOne, matchingUsernameUserTwo);

        UserEntity notMatchingUsernameUser = UserObjectProvider.provideUserEntity();
        notMatchingUsernameUser.setUsername(NOT_MATCHING_USERNAME);
        List<UserEntity> nonMatchingUsers = List.of(notMatchingUsernameUser);

        userRepository.saveAll(Stream.concat(matchingUsers.stream(), nonMatchingUsers.stream()).toList());

        // when
        EntityExchangeResult<List<UserDto>> result = webTestClient.get()
                .uri(USERS_URL + "/search/" + USERNAME_TO_MATCH)
                .header("Authorization", "Bearer " + jwtTokenProvider.provideToken())
                .exchange()
                .expectBodyList(UserDto.class)
                .returnResult();

        // then
        assertNotNull(result);
        assertNotNull(result.getResponseBody());
        assertEquals(HttpStatus.OK, result.getStatus());
        assertEquals(matchingUsers.size(), result.getResponseBody().size());
        assertTrue(matchingUsers.stream().allMatch(matchingUser -> comparePersistedUserEntityToResponseBody(matchingUser, result.getResponseBody())));
    }

    private boolean comparePersistedUserEntityToResponseBody(UserEntity persistedUser, List<UserDto> result) {
        return result.stream()
                .anyMatch(user -> user.getUsername().equals(persistedUser.getUsername()) &&
                        user.getEmail().equals(persistedUser.getEmail()) &&
                        user.getFirstName().equals(persistedUser.getFirstName()) &&
                        user.getLastName().equals(persistedUser.getLastName())
                );
    }
}