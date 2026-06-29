package br.com.myshelf.backend.infrastructure.web.controller;

import br.com.myshelf.backend.application.dto.book.BookDTO;
import br.com.myshelf.backend.application.dto.book.BookRegisterDTO;
import br.com.myshelf.backend.application.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> register(@RequestBody @Valid BookRegisterDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(dto));
    }
}
