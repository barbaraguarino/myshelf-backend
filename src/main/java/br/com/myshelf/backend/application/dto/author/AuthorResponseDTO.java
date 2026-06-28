package br.com.myshelf.backend.application.dto.author;

import java.util.UUID;

public record AuthorResponseDTO(
        UUID id,
        String name
){}