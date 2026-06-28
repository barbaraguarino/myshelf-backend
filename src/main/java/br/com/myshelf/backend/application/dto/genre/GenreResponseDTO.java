package br.com.myshelf.backend.application.dto.genre;

import java.util.UUID;

public record GenreResponseDTO(
        UUID id,
        String name
){}