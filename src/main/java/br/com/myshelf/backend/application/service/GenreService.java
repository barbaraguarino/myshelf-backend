package br.com.myshelf.backend.application.service;

import br.com.myshelf.backend.application.dto.genre.GenreAddDTO;
import br.com.myshelf.backend.application.dto.genre.GenreListAddDTO;
import br.com.myshelf.backend.application.dto.genre.GenreListResponseDTO;
import br.com.myshelf.backend.application.dto.genre.GenreResponseDTO;
import br.com.myshelf.backend.application.mapper.GenreMapper;
import br.com.myshelf.backend.domain.exception.ResourceAlreadyExistsException;
import br.com.myshelf.backend.domain.model.Genre;
import br.com.myshelf.backend.domain.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<String> names = genreListAddDTO.genreList().stream()
                .map(dto -> dto.name().trim())
                .toList();

        List<Genre> existingGenres = genreRepository.findByNameInIgnoreCase(names);

        Map<String, Genre> existingGenresMap = existingGenres.stream()
                .collect(Collectors.toMap(
                        genre -> genre.getName().trim().toLowerCase(),
                        genre -> genre
                ));

        List<Genre> allGenresReturn = new ArrayList<>();
        List<Genre> newGenres = new ArrayList<>();

        for (String name : names) {
            String nameKey = name.toLowerCase();

            if (existingGenresMap.containsKey(nameKey)) {
                allGenresReturn.add(existingGenresMap.get(nameKey));
            } else {
                Genre newGenre = Genre.createGenre(name);
                newGenres.add(newGenre);

                existingGenresMap.put(nameKey, newGenre);
            }
        }

        if (!newGenres.isEmpty()) {
            List<Genre> savedGenres = genreRepository.saveAll(newGenres);
            allGenresReturn.addAll(savedGenres);
        }

        return genreMapper.toResponseDTO(allGenresReturn);
    }
}
