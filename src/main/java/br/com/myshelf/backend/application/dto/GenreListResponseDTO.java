package br.com.myshelf.backend.application.dto;

import java.util.List;

public record GenreListResponseDTO (
    List<GenreResponseDTO> genres
){}
