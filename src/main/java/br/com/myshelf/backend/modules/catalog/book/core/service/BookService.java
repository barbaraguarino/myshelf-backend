package br.com.myshelf.backend.modules.catalog.book.core.service;

import br.com.myshelf.backend.core.exception.domain.ResourceAlreadyExistsException;
import br.com.myshelf.backend.modules.catalog.author.core.model.Author;
import br.com.myshelf.backend.modules.catalog.author.core.service.AuthorService;
import br.com.myshelf.backend.modules.catalog.book.api.dto.BookResponseDTO;
import br.com.myshelf.backend.modules.catalog.book.api.mapper.BookMapper;
import br.com.myshelf.backend.modules.catalog.book.api.dto.BookRegisterDTO;
import br.com.myshelf.backend.modules.catalog.book.core.model.Book;
import br.com.myshelf.backend.modules.catalog.book.data.BookRepository;
import br.com.myshelf.backend.modules.catalog.genre.core.model.Genre;
import br.com.myshelf.backend.modules.catalog.genre.core.service.GenreService;
import br.com.myshelf.backend.modules.catalog.publisher.core.model.Publisher;
import br.com.myshelf.backend.modules.catalog.publisher.core.service.PublisherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherService publisherService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookMapper bookMapper;

    @Transactional
    public BookResponseDTO createBook(BookRegisterDTO dto) {

        if (bookRepository.existsByCodeIgnoreCase(dto.code().trim())) {
            throw new ResourceAlreadyExistsException("Livro", "ISBN/código", dto.code());
        }

        Publisher publisher = publisherService.findPublisherById(dto.publisher().id());

        List<Author> authors = dto.authors().stream()
                .map(authorDTO -> authorService.findAuthorById(authorDTO.id()))
                .toList();


        List<Genre> genres = dto.genres().stream()
                .map(genreID -> genreService
                        .findGenreById(genreID.id()))
                .toList();

        Book book = bookMapper.toEntity(
                dto,
                publisher,
                authors,
                genres
        );

        return bookMapper.toResponseDTO(bookRepository.save(book));
    }

    @Transactional(readOnly = true)
    public Page<BookResponseDTO> listAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(bookMapper::toResponseDTO);
    }
}
