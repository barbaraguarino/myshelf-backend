package br.com.myshelf.backend.modules.catalog.publisher.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PublisherRequestDTO(
        @NotNull(message = "O ID da editora é obrigatório.")
        UUID id
){}
