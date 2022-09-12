package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.dto.auth.ForgotPasswordDto;
import com.kacperKwiatkowski.tweetApp.mapper.AuthMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class MailService {

    private final JavaMailSender emailSender;
    private final AuthMapper authMapper;

    public void remindPassword(String jsonForgottenPasswordDto) {

        ForgotPasswordDto forgotPasswordDto = authMapper.mapStringToForgottenPasswordDto(jsonForgottenPasswordDto);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kacper.kwiatkowski.cognizant@gmail.com");
        message.setTo("kacper18k@gmail.com");
        message.setSubject("PASSWORD RESET");
        message.setText("Username: " + forgotPasswordDto.getUsername() + "\n" + "New password: " + forgotPasswordDto.getPassword());
        emailSender.send(message);
    }
}
