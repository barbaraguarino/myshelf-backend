package br.com.myshelf.backend.application.service;

import br.com.myshelf.backend.domain.repository.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BookService{

    private final BookRepository bookRepository;
}
