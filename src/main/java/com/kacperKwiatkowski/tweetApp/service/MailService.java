package com.kacperKwiatkowski.tweetApp.service;

import com.kacperKwiatkowski.tweetApp.dto.auth.ForgotPasswordDto;
import com.kacperKwiatkowski.tweetApp.mapper.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private AuthMapper authMapper;

    public void remindPassword(String password) {

        ForgotPasswordDto forgotPasswordDto = authMapper.mapStringToForgottenPasswordDto(password);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo("kacper18k@gmail.com");
        message.setSubject("SUB");
        message.setText("Username: " + forgotPasswordDto.getUsername() + "\n" + "New password: " + forgotPasswordDto.getPassword());
        emailSender.send(message);

    }
}
