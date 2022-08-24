package com.kacperKwiatkowski.tweetApp;

import com.kacperKwiatkowski.tweetApp.model.TweetEntity;
import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import com.kacperKwiatkowski.tweetApp.repository.TweetRepository;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import com.kacperKwiatkowski.tweetApp.security.role.RoleType;
import lombok.AllArgsConstructor;
import org.bson.types.Binary;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableMongoRepositories
@AllArgsConstructor
@Configuration
public class TweetAppApplication {

    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    private final PasswordEncoder passwordEncoder;

    private static long staticDaysMinusCount = 10;
    private static long staticTitleCount = 1;
    private static long staticMessageCount = 1;


    private static final String PATTERN = "dd.MM.yyyy HH:mm:ss:SSS";

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
                            .firstName("Adam")
                            .lastName("Adamski")
                            .email("adam@gmail.com")
                            .username("4d4m")
                            .password(passwordEncoder.encode("password1"))
                            .avatar(getBinaryImage("https://img.freepik.com/premium-vector/man-avatar-profile-round-icon_24640-14044.jpg?w=740"))
                            .roleType(RoleType.USER)
                            .build()
            );


            userRepository.save(
                    UserEntity.builder()
                            .id(UUID.randomUUID())
                            .firstName("Maria")
                            .lastName("Kowalska")
                            .email("maria@gmail.com")
                            .username("m4ria")
                            .password(passwordEncoder.encode("password2"))
                            .avatar(getBinaryImage("https://img.freepik.com/premium-vector/woman-avatar-profile-round-icon_24640-14047.jpg?w=740"))
                            .roleType(RoleType.USER)
                            .build()
            );

            userRepository.save(
                    UserEntity.builder()
                            .id(UUID.randomUUID())
                            .firstName("Bożena")
                            .lastName("Czartoryska")
                            .email("bozena@gmail.com")
                            .username("b0zen4")
                            .password(passwordEncoder.encode("password3"))
                            .avatar(getBinaryImage("https://img.freepik.com/premium-vector/woman-avatar-profile-round-icon_24640-14042.jpg?w=740"))
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
                                        .postDateTime(LocalDateTime.now().minusDays(staticDaysMinusCount++).format(DateTimeFormatter.ofPattern(PATTERN)))
                                        .username("4d4m")
                                        .threadId(UUID.randomUUID())
                                        .build()
                        );

                        tweetRepository.save(
                                TweetEntity.builder()
                                        .tweetId(UUID.randomUUID())
                                        .title("TITLE_" + staticTitleCount++)
                                        .message("MESSAGE_" + staticMessageCount++)
                                        .postDateTime(LocalDateTime.now().minusDays(staticDaysMinusCount++).format(DateTimeFormatter.ofPattern(PATTERN)))
                                        .username("m4ria")
                                        .threadId(thread1)
                                        .build()
                        );

                        tweetRepository.save(
                                TweetEntity.builder()
                                        .tweetId(UUID.randomUUID())
                                        .title("TITLE_" + staticTitleCount++)
                                        .message("MESSAGE_" + staticMessageCount++)
                                        .postDateTime(LocalDateTime.now().minusDays(staticDaysMinusCount++).format(DateTimeFormatter.ofPattern(PATTERN)))
                                        .username("b0zen4")
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
                            .postDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN)))
                            .username("4d4m")
                            .threadId(thread1)
                            .build()
            );

            tweetRepository.save(
                    TweetEntity.builder()
                            .tweetId(UUID.randomUUID())
                            .title("TITLE_" + staticTitleCount++)
                            .message("MESSAGE_" + staticMessageCount++)
                            .postDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN)))
                            .username("4d4m")
                            .threadId(thread1)
                            .build()
            );

            tweetRepository.save(
                    TweetEntity.builder()
                            .tweetId(UUID.randomUUID())
                            .title("TITLE_" + staticTitleCount++)
                            .message("SHOULD BE FIRST")
                            .postDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN)))
                            .username("m4ria")
                            .threadId(UUID.randomUUID())
                            .build()
            );
        };
    }

    private Binary getBinaryImage(String path) {
        try {
            URL url = new URL(path);
            InputStream is = url.openStream();
            return new Binary(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException();
            // return new Binary();
        }
    }
}
