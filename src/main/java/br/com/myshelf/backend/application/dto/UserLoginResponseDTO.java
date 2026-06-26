package br.com.myshelf.backend.application.dto;

import java.sql.Timestamp;

public record UserLoginResponseDTO(
        String name,
        String nickname,
        String email,
        Timestamp createdAccount
){}
