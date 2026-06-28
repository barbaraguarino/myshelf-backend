package br.com.myshelf.backend.application.dto.publisher;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record PublisherListAddDTO (
        @NotEmpty(message = "A lista de editoras não pode estar vazia.")
        @Valid
        List<PublisherAddDTO> publishers
){}
