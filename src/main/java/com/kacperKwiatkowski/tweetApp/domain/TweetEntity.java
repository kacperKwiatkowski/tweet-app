package com.kacperKwiatkowski.tweetApp.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class TweetEntity {

    @Id
    private UUID id;
    private String userId;
    private String avatar;
    private String title;
    private String message;
    private LocalDateTime postDateTime;
}
