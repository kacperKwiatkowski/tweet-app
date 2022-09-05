package com.kacperKwiatkowski.tweetApp.message.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
@Profile("debug")
public class ConsumerConfig {

//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
}