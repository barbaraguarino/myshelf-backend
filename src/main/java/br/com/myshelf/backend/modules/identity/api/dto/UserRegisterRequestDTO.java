package br.com.myshelf.backend.modules.identity.api.dto;

import jakarta.validation.constraints.*;

public record  UserRegisterRequestDTO(

        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres.")
        String name,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Formato de e-mail invalido.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 8, max = 50, message = "A senha deve ter entre 8 e 50 caracteres.")
        @Pattern(
                regexp = "^(?=.*[a-z]).*$",
                message = "A senha deve conter pelo menos uma letra minúscula."
        )
        @Pattern(
                regexp = "^(?=.*[A-Z]).*$",
                message = "A senha deve conter pelo menos uma letra maiúscula."
        )
        @Pattern(
                regexp = "^(?=.*\\d).*$",
                message = "A senha deve conter pelo menos um número."
        )
        @Pattern(
                regexp = "^(?=.*[@$!%*?&.#_-]).*$",
                message = "A senha deve conter pelo menos um caractere especial."
        )
        String password

){}
