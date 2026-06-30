package br.com.myshelf.backend.modules.catalog.book.api.dto;

import br.com.myshelf.backend.modules.catalog.author.api.dto.AuthorResponseDTO;
import br.com.myshelf.backend.modules.catalog.genre.api.dto.GenreResponseDTO;
import br.com.myshelf.backend.modules.catalog.publisher.api.dto.PublisherResponseDTO;

import java.util.List;
import java.util.UUID;

public record BookResponseDTO(
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
