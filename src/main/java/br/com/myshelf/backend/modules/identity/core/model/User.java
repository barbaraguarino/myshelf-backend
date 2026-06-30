package br.com.myshelf.backend.modules.identity.core.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


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
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
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
