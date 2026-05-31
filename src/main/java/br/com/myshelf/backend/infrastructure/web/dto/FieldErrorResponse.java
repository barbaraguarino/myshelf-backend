package br.com.myshelf.backend.infrastructure.web.dto;

public record FieldErrorResponse(
        String field,
        String code,
        String message
){}
