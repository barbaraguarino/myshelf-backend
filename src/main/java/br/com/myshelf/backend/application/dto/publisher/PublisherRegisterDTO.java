package br.com.myshelf.backend.application.dto.publisher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PublisherRegisterDTO(
        @NotBlank(message = "O nome da editora é obrigatório.")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 a 100 caracteres.")
        String name
){}
