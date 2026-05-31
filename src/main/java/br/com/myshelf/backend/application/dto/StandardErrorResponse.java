package br.com.myshelf.backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StandardErrorResponse(
        Instant timestamp,
        Integer status,
        String errorCode,
        String error,
        String message,
        String path,
        List<FieldErrorResponse> validationsErros
){}
