package com.kacperKwiatkowski.tweetApp.message.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kacperKwiatkowski.tweetApp.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @Autowired
    private MailService mailService;

    private static final String TOPIC_FORGOTTEN_PASSWORD = "user.password.forgotten";

    @Autowired
    public Consumer() {
    }

    @KafkaListener(topics = TOPIC_FORGOTTEN_PASSWORD)
    public void consumeUserMessage(String message) {
        mailService.remindPassword(message);
    }
}