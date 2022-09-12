package com.kacperKwiatkowski.tweetApp.message.consumer;

import com.kacperKwiatkowski.tweetApp.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class SqsReceiver {

    private final MailService mailService;

    @SqsListener(value = "forgottenPassword",deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processMessage(String message) {
        log.info("Message from SQS {}", message);
        mailService.remindPassword(message);
    }
}
