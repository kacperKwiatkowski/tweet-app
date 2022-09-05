package com.kacperKwiatkowski.tweetApp.message.consumer;

import com.kacperKwiatkowski.tweetApp.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("debug")
public class Consumer {

    @Autowired
    private MailService mailService;

    private static final String TOPIC_FORGOTTEN_PASSWORD = "user.password.forgotten";

    @Autowired
    public Consumer() {
    }

    @KafkaListener(topics = TOPIC_FORGOTTEN_PASSWORD, autoStartup = "false")
    public void consumeUserMessage(String message) {
        mailService.remindPassword(message);
    }
}