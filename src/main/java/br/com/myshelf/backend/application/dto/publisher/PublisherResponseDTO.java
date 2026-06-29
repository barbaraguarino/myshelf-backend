package br.com.myshelf.backend.application.dto.publisher;

import java.util.UUID;

public record PublisherResponseDTO(
        UUID id,
        String name
){}
