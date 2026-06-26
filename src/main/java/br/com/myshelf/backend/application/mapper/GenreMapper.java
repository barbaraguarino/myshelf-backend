package br.com.myshelf.backend.application.mapper;

import br.com.myshelf.backend.application.dto.GenreAddDTO;
import br.com.myshelf.backend.application.dto.GenreListAddDTO;
import br.com.myshelf.backend.application.dto.GenreListResponseDTO;
import br.com.myshelf.backend.application.dto.GenreResponseDTO;
import br.com.myshelf.backend.domain.model.Genre;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapper {

    public Genre toEntity(GenreAddDTO dto) {
        if (dto == null) return null;
        return Genre.createGenre(dto.name());
    }

    public List<Genre> toEntity(GenreListAddDTO dto) {
        if (dto == null || dto.genreList() == null) return Collections.emptyList();

        return dto.genreList().stream()
                .map(genre -> Genre.createGenre(genre.name()))
                .collect(Collectors.toList());
    }

    public GenreResponseDTO toResponseDTO(Genre genre) {
        if (genre == null) return null;
        return new GenreResponseDTO(genre.getId(), genre.getName());
    }

    public GenreListResponseDTO toResponseDTO(List<Genre> genres) {
        if (genres == null) return new GenreListResponseDTO(Collections.emptyList());

        List<GenreResponseDTO> list = genres.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

        return new GenreListResponseDTO(list);
    }
}
