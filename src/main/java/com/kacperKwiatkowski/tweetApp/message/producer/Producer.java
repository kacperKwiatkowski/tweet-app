package com.kacperKwiatkowski.tweetApp.message.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("debug")
public class Producer {

    private static final String TOPIC_FORGOTTEN_PASSWORD = "user.password.forgotten";

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendMessage() throws JsonProcessingException {
        kafkaTemplate.send(TOPIC_FORGOTTEN_PASSWORD, "MESSAGE_TEST");

        log.info("food order produced {}", "MESSAGE_TEST");

        return "message sent";
    }
}
