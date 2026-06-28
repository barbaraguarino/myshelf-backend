package br.com.myshelf.backend.application.dto.publisher;

import java.util.List;

public record PublisherListResponseDTO (
        List<PublisherResponseDTO> publishers
){}
