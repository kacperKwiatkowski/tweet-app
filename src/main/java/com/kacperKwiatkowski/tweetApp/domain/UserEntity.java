package com.kacperKwiatkowski.tweetApp.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class UserEntity {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
