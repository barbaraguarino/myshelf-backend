package br.com.myshelf.backend.application.dto.author;

import java.util.List;

public record AuthorListResponseDTO (
        List<AuthorResponseDTO> authors
){}
