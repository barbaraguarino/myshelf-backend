package br.com.myshelf.backend.modules.catalog.author.api.dto;

import java.util.List;

public record ListAuthorDTO(
        List<AuthorResponseDTO> authors
){}
