package br.com.myshelf.backend.modules.catalog.author.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorRegisterDTO(
        @NotBlank(message = "O nome do autor é obrigatório.")
        @Size(min = 2, max = 150, message = "O nome deve ter entre 2 a 150 caracteres.")
        String name
){}
