package br.com.myshelf.backend.application.dto;

import jakarta.validation.Valid;

import java.util.List;

public record GenreListAddDTO (
    @Valid
    List<GenreAddDTO> genreList
){}
