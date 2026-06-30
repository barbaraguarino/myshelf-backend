package br.com.myshelf.backend.modules.catalog.author.api.dto;

import java.util.List;

public record ListAuthorResponseDTO(
        List<AuthorResponseDTO> authors
){}
