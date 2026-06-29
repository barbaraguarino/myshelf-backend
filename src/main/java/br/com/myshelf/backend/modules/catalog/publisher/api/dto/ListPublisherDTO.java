package br.com.myshelf.backend.modules.catalog.publisher.api.dto;

import java.util.List;

public record ListPublisherDTO(
        List<PublisherResponseDTO> publishers
){}
