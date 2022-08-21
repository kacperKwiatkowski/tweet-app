package com.kacperKwiatkowski.tweetApp;

import com.kacperKwiatkowski.tweetApp.model.TweetEntity;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import com.kacperKwiatkowski.tweetApp.repository.TweetRepository;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import com.kacperKwiatkowski.tweetApp.security.role.RoleType;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
@EnableMongoRepositories
@AllArgsConstructor
public class TweetAppApplication {

    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    private final PasswordEncoder passwordEncoder;

    static long staticDaysMinusCount = 10;
    static long staticTitleCount = 1;
    static long staticMessageCount = 1;

    public static void main(String[] args) {
        SpringApplication.run(TweetAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            userRepository.deleteAll();
            tweetRepository.deleteAll();

            userRepository.save(
                    UserEntity.builder()
                            .id(UUID.randomUUID())
                            .email("email1")
                            .username("username1")
                            .password(passwordEncoder.encode("password1"))
                            .roleType(RoleType.USER)
                            .build()
            );

            userRepository.save(
                    UserEntity.builder()
                            .id(UUID.randomUUID())
                            .email("email2")
                            .username("username2")
                            .password(passwordEncoder.encode("password2"))
                            .roleType(RoleType.USER)
                            .build()
            );

            userRepository.save(
                    UserEntity.builder()
                            .id(UUID.randomUUID())
                            .email("email3")
                            .username("username3")
                            .password(passwordEncoder.encode("password3"))
                            .roleType(RoleType.USER)
                            .build()
            );

            UUID thread1 = UUID.randomUUID();

            userRepository.findAll().forEach(
                    user -> {
                        tweetRepository.save(
                                TweetEntity.builder()
                                        .tweetId(UUID.randomUUID())
                                        .title("TITLE_" + staticTitleCount++)
                                        .message("MESSAGE_" + staticMessageCount++)
                                        .postDateTime(LocalDateTime.now().minusDays(staticDaysMinusCount++))
                                        .username("username1")
                                        .threadId(UUID.randomUUID())
                                        .build()
                        );

                        tweetRepository.save(
                                TweetEntity.builder()
                                        .tweetId(UUID.randomUUID())
                                        .title("TITLE_" + staticTitleCount++)
                                        .message("MESSAGE_" + staticMessageCount++)
                                        .postDateTime(LocalDateTime.now().minusDays(staticDaysMinusCount++))
                                        .username("username2")
                                        .threadId(thread1)
                                        .build()
                        );
                    }
            );

            tweetRepository.save(
                    TweetEntity.builder()
                            .tweetId(UUID.randomUUID())
                            .title("TITLE_" + staticTitleCount++)
                            .message("MESSAGE_" + staticMessageCount++)
                            .postDateTime(LocalDateTime.now())
                            .username("username1")
                            .threadId(thread1)
                            .build()
            );

            tweetRepository.save(
                    TweetEntity.builder()
                            .tweetId(UUID.randomUUID())
                            .title("TITLE_" + staticTitleCount++)
                            .message("MESSAGE_" + staticMessageCount++)
                            .postDateTime(LocalDateTime.now())
                            .username("username1")
                            .threadId(thread1)
                            .build()
            );

            tweetRepository.save(
                    TweetEntity.builder()
                            .tweetId(UUID.randomUUID())
                            .title("TITLE_" + staticTitleCount++)
                            .message("SHOULD BE FIRST")
                            .postDateTime(LocalDateTime.now().plusDays(2))
                            .username("username1")
                            .threadId(UUID.randomUUID())
                            .build()
            );
        };
    }
}
