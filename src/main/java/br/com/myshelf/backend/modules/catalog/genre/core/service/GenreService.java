package br.com.myshelf.backend.modules.catalog.genre.core.service;

import br.com.myshelf.backend.core.exception.domain.ResourceAlreadyExistsException;
import br.com.myshelf.backend.core.exception.domain.ResourceNotFoundException;
import br.com.myshelf.backend.modules.catalog.genre.api.dto.ListGenreDTO;
import br.com.myshelf.backend.modules.catalog.genre.api.dto.ListGenreRegisterDTO;
import br.com.myshelf.backend.modules.catalog.genre.api.dto.GenreRegisterDTO;
import br.com.myshelf.backend.modules.catalog.genre.api.dto.GenreResponseDTO;
import br.com.myshelf.backend.modules.catalog.genre.api.mapper.GenreMapper;
import br.com.myshelf.backend.modules.catalog.genre.core.model.Genre;
import br.com.myshelf.backend.modules.catalog.genre.data.GenreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreResponseDTO createGenre(GenreRegisterDTO genreRegisterDTO){
        if(genreRepository.existsByNameIgnoreCase(genreRegisterDTO.name().trim()))
            throw new ResourceAlreadyExistsException("Genre", "nome", genreRegisterDTO.name().trim());

        Genre genre = genreMapper.toEntity(genreRegisterDTO);
        genre = genreRepository.save(genre);

        return genreMapper.toResponseDTO(genre);
    }

    public ListGenreDTO createGenreList(ListGenreRegisterDTO listGenreRegisterDTO) {
        List<String> names = listGenreRegisterDTO.genreList().stream()
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

    public Genre findGenreById(UUID genreId){
        return genreRepository.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("Gênero literário", genreId));
    }
}
