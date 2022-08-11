package com.kacperKwiatkowski.tweetApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document("users")
public class UserEntity {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
