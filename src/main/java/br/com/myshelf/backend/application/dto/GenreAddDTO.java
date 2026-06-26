package br.com.myshelf.backend.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GenreAddDTO(
        @NotBlank(message = "O nome do gênero é obrigatório.")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 a 100 caracteres.")
        String name
){}
