package br.com.myshelf.backend.modules.catalog.book.api.controller;

import br.com.myshelf.backend.modules.catalog.book.api.dto.BookResponseDTO;
import br.com.myshelf.backend.modules.catalog.book.api.dto.BookRegisterDTO;
import br.com.myshelf.backend.modules.catalog.book.core.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDTO> register(@RequestBody @Valid BookRegisterDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(dto));
    }

    @GetMapping
    public ResponseEntity<Page<BookResponseDTO>> listAllBooks(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.listAllBooks(pageable));
    }
}
