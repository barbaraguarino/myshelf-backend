package br.com.myshelf.backend.application.dto.genre;

import java.util.UUID;

public record GenreDTO(
        UUID id,
        String name
){}