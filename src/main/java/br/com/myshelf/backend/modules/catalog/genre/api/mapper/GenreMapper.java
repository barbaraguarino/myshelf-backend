package br.com.myshelf.backend.modules.catalog.genre.api.mapper;

import br.com.myshelf.backend.modules.catalog.genre.api.dto.GenreRegisterDTO;
import br.com.myshelf.backend.modules.catalog.genre.api.dto.GenreResponseDTO;
import br.com.myshelf.backend.modules.catalog.genre.api.dto.ListGenreResponseDTO;
import br.com.myshelf.backend.modules.catalog.genre.core.model.Genre;
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

    public ListGenreResponseDTO toResponseDTO(List<Genre> genres) {
        if (genres == null) return new ListGenreResponseDTO(Collections.emptyList());

        List<GenreResponseDTO> list = genres.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

        return new ListGenreResponseDTO(list);
    }
}
