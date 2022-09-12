package com.kacperKwiatkowski.tweetApp.message.publisher;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class SqsPublisher {

    @Value("${amazon.sqs.endpoint}")
    private String sqsEndpoint;

    @Value("${topic.forgottenPassword}")
    private String topicName;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Scheduled(fixedRate = 1000)
    public <T> void send(String message) {
        log.info("Sending Message to SQS ");

        queueMessagingTemplate.convertAndSend(sqsEndpoint + topicName, message);
    }
}
