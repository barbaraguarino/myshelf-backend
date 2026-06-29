package br.com.myshelf.backend.application.dto.author;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AuthorRequestDTO(
        @NotNull(message = "O ID do(a) autor(a) é obrigatório.")
        UUID id,
        @NotNull(message = "O nome do(a) autor(s) é obrigatório.")
        String name
) {
}
