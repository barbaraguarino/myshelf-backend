package br.com.myshelf.backend.domain.model;

import jakarta.persistence.*;
import lombok.*;


import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public static User createUser(String name,
                                  String nickname,
                                  String email,
                                  String password) {
        return User.builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .password(password)
                .build();
    }

}
