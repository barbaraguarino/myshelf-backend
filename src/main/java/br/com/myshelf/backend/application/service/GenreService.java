package br.com.myshelf.backend.application.service;

import br.com.myshelf.backend.application.dto.GenreAddDTO;
import br.com.myshelf.backend.application.dto.GenreListAddDTO;
import br.com.myshelf.backend.application.dto.GenreListResponseDTO;
import br.com.myshelf.backend.application.dto.GenreResponseDTO;
import br.com.myshelf.backend.application.mapper.GenreMapper;
import br.com.myshelf.backend.domain.exception.ResourceAlreadyExistsException;
import br.com.myshelf.backend.domain.model.Genre;
import br.com.myshelf.backend.domain.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreResponseDTO createGenre(GenreAddDTO genreAddDTO){
        if(genreRepository.existsByNameIgnoreCase(genreAddDTO.name().trim()))
            throw new ResourceAlreadyExistsException("Genre", "nome", genreAddDTO.name().trim());

        Genre genre = genreMapper.toEntity(genreAddDTO);
        genre = genreRepository.save(genre);

        return genreMapper.toResponseDTO(genre);
    }

    public GenreListResponseDTO createGenreList(GenreListAddDTO genreListAddDTO) {
        List<GenreResponseDTO> genres = genreListAddDTO.genreList().stream()
                .map(genreDTO -> {
                    String cleanName = genreDTO.name().trim();

                    Genre genre = genreRepository.existsByNameIgnoreCase(cleanName)
                            ? genreRepository.findByNameIgnoreCase(cleanName)
                            : genreRepository.save(genreMapper.toEntity(genreDTO));

                    return genreMapper.toResponseDTO(genre);
                })
                .toList();

        return new GenreListResponseDTO(genres);
    }
}
