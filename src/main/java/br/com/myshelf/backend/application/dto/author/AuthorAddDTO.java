package br.com.myshelf.backend.application.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorAddDTO(
        @NotBlank(message = "O nome do autor é obrigatório.")
        @Size(min = 2, max = 150, message = "O nome deve ter entre 2 a 150 caracteres.")
        String name
){}
