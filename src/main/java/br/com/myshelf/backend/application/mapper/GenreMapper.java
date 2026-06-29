package br.com.myshelf.backend.application.mapper;

import br.com.myshelf.backend.application.dto.genre.GenreRegisterDTO;
import br.com.myshelf.backend.application.dto.genre.ListGenreDTO;
import br.com.myshelf.backend.application.dto.genre.GenreResponseDTO;
import br.com.myshelf.backend.domain.model.Genre;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapper {

    public Genre toEntity(GenreRegisterDTO dto) {
        if (dto == null) return null;
        return Genre.createGenre(dto.name());
    }

    public GenreResponseDTO toResponseDTO(Genre genre) {
        if (genre == null) return null;
        return new GenreResponseDTO(genre.getId(), genre.getName());
    }

    public ListGenreDTO toResponseDTO(List<Genre> genres) {
        if (genres == null) return new ListGenreDTO(Collections.emptyList());

        List<GenreResponseDTO> list = genres.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

        return new ListGenreDTO(list);
    }
}
