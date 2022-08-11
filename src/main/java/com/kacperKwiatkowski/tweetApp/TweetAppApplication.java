package com.kacperKwiatkowski.tweetApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class TweetAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TweetAppApplication.class, args);
    }

}
