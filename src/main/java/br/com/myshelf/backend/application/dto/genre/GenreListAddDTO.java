package br.com.myshelf.backend.application.dto.genre;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record GenreListAddDTO (
    @NotEmpty(message = "A lista de gêneros literários não pode estar vazia.")
    @Valid
    List<GenreAddDTO> genreList
){}
