package br.com.myshelf.backend.application.dto.book;

import br.com.myshelf.backend.application.dto.genre.GenreDTO;
import br.com.myshelf.backend.application.dto.publisher.PublisherDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record BookRegisterDTO(
        @NotBlank(message = "O código/ISBN é obrigatório.")
        @Size(min = 10, max = 13, message = "O ISBN deve ter entre 10 e 13 caracteres.")
        String code,

        @NotBlank(message = "O título do livro é obrigatório.")
        @Size(min = 2, max = 255, message = "O título deve ter entre 2 e 255 caracteres.")
        String title,

        @NotBlank(message = "O formato do livro é obrigatório.")
        String format,

        @Positive(message = "A quantidade de páginas deve ser um número positivo.")
        int pages,

        @Min(value = 1, message = "A edição deve ser maior ou igual a 1.")
        int edition,

        String summary,

        @Size(min = 2, max = 5, message = "O idioma deve ser uma sigla válida (ex: pt-BR).")
        String language,

        @NotNull(message = "O ano de publicação é obrigatório.")
        @Min(value = 1450, message = "O ano deve ser posterior à invenção da imprensa.")
        Integer publicationYear,

        @NotNull(message = "A editora é obrigatória.")
        @Valid PublisherDTO publisher,

        @NotEmpty(message = "Ao menos um autor deve ser informado.")
        @Valid List<PublisherDTO> authors,

        @NotEmpty(message = "Ao menos um gênero deve ser informado.")
        @Valid List<GenreDTO> genres
) {}
