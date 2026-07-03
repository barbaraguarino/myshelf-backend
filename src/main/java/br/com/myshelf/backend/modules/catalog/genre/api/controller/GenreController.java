package br.com.myshelf.backend.modules.catalog.genre.api.controller;

import br.com.myshelf.backend.modules.catalog.genre.api.dto.GenreRegisterDTO;
import br.com.myshelf.backend.modules.catalog.genre.api.dto.GenreResponseDTO;
import br.com.myshelf.backend.modules.catalog.genre.api.dto.ListGenreResponseDTO;
import br.com.myshelf.backend.modules.catalog.genre.api.dto.ListGenreRegisterDTO;
import br.com.myshelf.backend.modules.catalog.genre.core.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreResponseDTO> createGenre(@Valid @RequestBody GenreRegisterDTO genreRegisterDTO) {
        var genre = genreService.createGenre(genreRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genre);
    }

    @PostMapping("/list")
    public ResponseEntity<ListGenreResponseDTO> createGenreList(@Valid @RequestBody ListGenreRegisterDTO listGenreRegisterDTO){
        var genreList = genreService.createGenreList(listGenreRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreList);
    }

    @GetMapping
    public ResponseEntity<Page<GenreResponseDTO>> listAllGenres(Pageable pageable) {
        Page<GenreResponseDTO> pageResult = genreService.findAllPaged(pageable);
        return ResponseEntity.ok(pageResult);
    }
}
