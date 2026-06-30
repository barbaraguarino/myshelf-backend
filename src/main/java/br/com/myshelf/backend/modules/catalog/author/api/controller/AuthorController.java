package br.com.myshelf.backend.modules.catalog.author.api.controller;

import br.com.myshelf.backend.modules.catalog.author.api.dto.AuthorRegisterDTO;
import br.com.myshelf.backend.modules.catalog.author.api.dto.AuthorResponseDTO;
import br.com.myshelf.backend.modules.catalog.author.api.dto.ListAuthorResponseDTO;
import br.com.myshelf.backend.modules.catalog.author.api.dto.ListAuthorRegisterDTO;
import br.com.myshelf.backend.modules.catalog.author.core.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRegisterDTO authorRegisterDTO) {
        var author = authorService.createAuthor(authorRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    @PostMapping("/list")
    public ResponseEntity<ListAuthorResponseDTO> createAuthorList(@Valid @RequestBody ListAuthorRegisterDTO listAuthorRegisterDTO){
        var authorList = authorService.createAuthorList(listAuthorRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorList);
    }
}
