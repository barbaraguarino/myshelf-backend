package br.com.myshelf.backend.application.mapper;

import br.com.myshelf.backend.application.dto.author.AuthorRegisterDTO;
import br.com.myshelf.backend.application.dto.author.ListAuthorDTO;
import br.com.myshelf.backend.application.dto.author.AuthorDTO;
import br.com.myshelf.backend.domain.model.Author;
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

    public AuthorDTO toResponseDTO(Author author) {
        if (author == null) return null;
        return new AuthorDTO(author.getId(), author.getName());
    }

    public ListAuthorDTO toResponseDTO(List<Author> authors) {
        if (authors == null) return new ListAuthorDTO(Collections.emptyList());

        List<AuthorDTO> list = authors.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

        return new ListAuthorDTO(list);
    }
}
