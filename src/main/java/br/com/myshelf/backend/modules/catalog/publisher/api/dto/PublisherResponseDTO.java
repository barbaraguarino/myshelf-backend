package br.com.myshelf.backend.modules.catalog.publisher.api.dto;

import java.util.UUID;

public record PublisherResponseDTO(
        UUID id,
        String name
){}
