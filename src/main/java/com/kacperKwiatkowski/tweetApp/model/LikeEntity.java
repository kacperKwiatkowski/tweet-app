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
@Document("likes")
public class LikeEntity {

    @Id
    private UUID likeId;
    private String username;
    private UUID tweetId;
}
