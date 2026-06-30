package br.com.myshelf.backend.modules.catalog.author.api.dto;

import java.util.UUID;

public record AuthorResponseDTO(
        UUID id,
        String name
){}