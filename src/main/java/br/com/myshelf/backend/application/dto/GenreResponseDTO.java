package br.com.myshelf.backend.application.dto;

import java.util.UUID;

public record GenreResponseDTO(
        UUID id,
        String name
){}