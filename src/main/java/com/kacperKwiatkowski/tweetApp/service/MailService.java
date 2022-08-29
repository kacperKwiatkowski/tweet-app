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

    public void remindPassword(String jsonRegisterUserDto) {

        ForgotPasswordDto forgotPasswordDto = authMapper.mapStringToForgottenPasswordDto(jsonRegisterUserDto);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kacper.kwiatkowski.cognizant@gmail.com");
        message.setTo("kacper18k@gmail.com");
        message.setSubject("PASSWORD RESET");
        message.setText("Username: " + forgotPasswordDto.getUsername() + "\n" + "New password: " + forgotPasswordDto.getPassword());
        emailSender.send(message);
    }
}
