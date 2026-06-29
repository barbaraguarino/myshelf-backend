package br.com.myshelf.backend.infrastructure.web.controller;

import br.com.myshelf.backend.application.dto.genre.GenreRegisterDTO;
import br.com.myshelf.backend.application.dto.genre.ListGenreRegisterDTO;
import br.com.myshelf.backend.application.dto.genre.ListGenreDTO;
import br.com.myshelf.backend.application.dto.genre.GenreResponseDTO;
import br.com.myshelf.backend.application.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ListGenreDTO> createGenreList(@Valid @RequestBody ListGenreRegisterDTO listGenreRegisterDTO){
        var genreList = genreService.createGenreList(listGenreRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreList);
    }
}
