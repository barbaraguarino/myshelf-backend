package br.com.myshelf.backend.infrastructure.web.controller;

import br.com.myshelf.backend.application.dto.author.AuthorRegisterDTO;
import br.com.myshelf.backend.application.dto.author.ListAuthorRegisterDTO;
import br.com.myshelf.backend.application.dto.author.ListAuthorDTO;
import br.com.myshelf.backend.application.dto.author.AuthorResponseDTO;
import br.com.myshelf.backend.application.service.AuthorService;
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
    public ResponseEntity<ListAuthorDTO> createAuthorList(@Valid @RequestBody ListAuthorRegisterDTO listAuthorRegisterDTO){
        var authorList = authorService.createAuthorList(listAuthorRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorList);
    }
}
