package br.com.myshelf.backend.application.dto.auth;

import java.sql.Timestamp;

public record UserLoginResponseDTO(
        String name,
        String nickname,
        String email,
        Timestamp createdAccount
){}
