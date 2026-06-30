package br.com.myshelf.backend.modules.catalog.genre.api.dto;

import java.util.UUID;

public record GenreResponseDTO(
        UUID id,
        String name
){}