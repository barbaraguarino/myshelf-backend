package br.com.myshelf.backend.core.exception;

public record FieldErrorResponse(
        String field,
        String code,
        String message
){}
