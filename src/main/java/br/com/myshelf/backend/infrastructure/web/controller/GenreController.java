package br.com.myshelf.backend.infrastructure.web.controller;

import br.com.myshelf.backend.application.dto.GenreAddDTO;
import br.com.myshelf.backend.application.dto.GenreListAddDTO;
import br.com.myshelf.backend.application.dto.GenreListResponseDTO;
import br.com.myshelf.backend.application.dto.GenreResponseDTO;
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
    public ResponseEntity<GenreResponseDTO> createGenre(@Valid @RequestBody GenreAddDTO genreAddDTO) {
        var genre = genreService.createGenre(genreAddDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genre);
    }

    @PostMapping("/list")
    public ResponseEntity<GenreListResponseDTO> createGenreList(@Valid @RequestBody GenreListAddDTO genreListAddDTO){
        var genreList = genreService.createGenreList(genreListAddDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreList);
    }
}
