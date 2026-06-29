package br.com.myshelf.backend.application.dto.publisher;

import java.util.UUID;

public record PublisherDTO(
        UUID id,
        String name
){}
