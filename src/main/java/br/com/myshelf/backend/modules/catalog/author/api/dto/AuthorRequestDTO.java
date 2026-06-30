package br.com.myshelf.backend.modules.catalog.author.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AuthorRequestDTO(
        @NotNull(message = "O ID do(a) autor(a) é obrigatório.")
        UUID id
) {
}
