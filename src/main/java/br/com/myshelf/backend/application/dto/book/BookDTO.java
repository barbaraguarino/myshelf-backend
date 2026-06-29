package br.com.myshelf.backend.application.dto.book;

import br.com.myshelf.backend.application.dto.author.AuthorResponseDTO;
import br.com.myshelf.backend.application.dto.genre.GenreResponseDTO;
import br.com.myshelf.backend.application.dto.publisher.PublisherResponseDTO;

import java.util.List;
import java.util.UUID;

public record BookDTO(
        UUID id,
        String code,
        String title,
        String format,
        int pages,
        int edition,
        String summary,
        String language,
        Integer publicationYear,
        PublisherResponseDTO publisher,
        List<AuthorResponseDTO> authors,
        List<GenreResponseDTO> genres
){}
