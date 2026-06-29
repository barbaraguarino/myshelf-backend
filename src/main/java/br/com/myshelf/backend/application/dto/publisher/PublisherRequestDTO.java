package br.com.myshelf.backend.application.dto.publisher;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PublisherRequestDTO(
        @NotNull(message = "O ID da editora é obrigatório.")
        UUID id,
        @NotNull(message = "O nome da editora é obrigatório.")
        String name
){}
