package br.com.myshelf.backend.modules.catalog.genre.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GenreRequestDTO(
        @NotNull(message = "O ID dao gênero literário é obrigatório.")
        UUID id
) {
}
