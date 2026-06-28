package br.com.myshelf.backend.application.mapper;

import br.com.myshelf.backend.application.dto.author.AuthorAddDTO;
import br.com.myshelf.backend.application.dto.author.AuthorListResponseDTO;
import br.com.myshelf.backend.application.dto.author.AuthorResponseDTO;
import br.com.myshelf.backend.domain.model.Author;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorAddDTO dto) {
        if (dto == null) return null;
        return Author.createAuthor(dto.name());
    }

    public AuthorResponseDTO toResponseDTO(Author author) {
        if (author == null) return null;
        return new AuthorResponseDTO(author.getId(), author.getName());
    }

    public AuthorListResponseDTO toResponseDTO(List<Author> authors) {
        if (authors == null) return new AuthorListResponseDTO(Collections.emptyList());

        List<AuthorResponseDTO> list = authors.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

        return new AuthorListResponseDTO(list);
    }
}
