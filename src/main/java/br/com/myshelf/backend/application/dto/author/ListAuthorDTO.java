package br.com.myshelf.backend.application.dto.author;

import java.util.List;

public record ListAuthorDTO(
        List<AuthorResponseDTO> authors
){}
