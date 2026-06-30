package br.com.myshelf.backend.modules.catalog.publisher.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ListPublisherRegisterDTO(
        @NotEmpty(message = "A lista de editoras não pode estar vazia.")
        @Valid
        List<PublisherRegisterDTO> publishers
){}
