package br.com.myshelf.backend.application.dto.genre;

import java.util.List;

public record GenreListResponseDTO (
    List<GenreResponseDTO> genres
){}
