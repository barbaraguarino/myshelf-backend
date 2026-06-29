package br.com.myshelf.backend.application.dto.genre;

import java.util.List;

public record ListGenreDTO(
    List<GenreDTO> genres
){}
