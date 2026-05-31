package br.com.myshelf.backend.application.dto;

public record FieldErrorResponse(
        String field,
        String message
){}
