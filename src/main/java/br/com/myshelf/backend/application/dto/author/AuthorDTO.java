package br.com.myshelf.backend.application.dto.author;

import java.util.UUID;

public record AuthorDTO(
        UUID id,
        String name
){}