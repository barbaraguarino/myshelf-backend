package br.com.myshelf.backend.modules.catalog.author.api.mapper;

import br.com.myshelf.backend.modules.catalog.author.api.dto.AuthorRegisterDTO;
import br.com.myshelf.backend.modules.catalog.author.api.dto.AuthorResponseDTO;
import br.com.myshelf.backend.modules.catalog.author.api.dto.ListAuthorDTO;
import br.com.myshelf.backend.modules.catalog.author.core.model.Author;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorRegisterDTO dto) {
        if (dto == null) return null;
        return Author.createAuthor(dto.name());
    }

    public AuthorResponseDTO toResponseDTO(Author author) {
        if (author == null) return null;
        return new AuthorResponseDTO(author.getId(), author.getName());
    }

    public ListAuthorDTO toResponseDTO(List<Author> authors) {
        if (authors == null) return new ListAuthorDTO(Collections.emptyList());

        List<AuthorResponseDTO> list = authors.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

        return new ListAuthorDTO(list);
    }
}
