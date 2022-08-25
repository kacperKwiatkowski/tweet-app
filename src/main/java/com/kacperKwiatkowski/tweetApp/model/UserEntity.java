package com.kacperKwiatkowski.tweetApp.model;

import com.kacperKwiatkowski.tweetApp.security.role.RoleType;
import lombok.*;
import org.bson.types.Binary;
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
    private String contactNumber;
    private Binary avatar;
    private String password;
    private RoleType roleType;

    public UserEntity assignUserId() {
        this.id = UUID.randomUUID();
        return this;
    }

    public UserEntity assignRole() {
        this.roleType = RoleType.USER;
        return this;
    }
}
