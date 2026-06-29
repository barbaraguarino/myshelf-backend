package br.com.myshelf.backend.application.dto.author;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ListAuthorRegisterDTO(
        @NotEmpty(message = "A lista de autores não pode estar vazia.")
        @Valid
        List<AuthorRegisterDTO> authors
){}
