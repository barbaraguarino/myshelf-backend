package br.com.myshelf.backend.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Formato de e-mail invalido.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        String password

) {}
