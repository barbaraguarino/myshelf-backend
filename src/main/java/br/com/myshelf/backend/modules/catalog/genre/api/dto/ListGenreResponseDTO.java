package br.com.myshelf.backend.modules.catalog.genre.api.dto;

import java.util.List;

public record ListGenreResponseDTO(
    List<GenreResponseDTO> genres
){}
