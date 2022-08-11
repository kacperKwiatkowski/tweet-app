package com.kacperKwiatkowski.tweetApp;

import com.kacperKwiatkowski.tweetApp.model.UserEntity;
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

import java.util.UUID;

@SpringBootApplication
@EnableMongoRepositories
@AllArgsConstructor
public class TweetAppApplication {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(TweetAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            userRepository.deleteAll();
            userRepository.save(
                    UserEntity.builder()
                            .id(UUID.randomUUID())
                            .email("email")
                            .username("username")
                            .password(passwordEncoder.encode("password"))
                            .roleType(RoleType.USER)
                            .build()
            );
        };
    }
}
