package br.com.myshelf.backend.modules.catalog.book.core.service;

import br.com.myshelf.backend.core.exception.domain.ResourceAlreadyExistsException;
import br.com.myshelf.backend.core.exception.domain.ResourceNotFoundException;
import br.com.myshelf.backend.modules.catalog.author.core.model.Author;
import br.com.myshelf.backend.modules.catalog.book.api.dto.BookDTO;
import br.com.myshelf.backend.modules.catalog.book.api.mapper.BookMapper;
import br.com.myshelf.backend.modules.catalog.book.api.dto.BookRegisterDTO;
import br.com.myshelf.backend.modules.catalog.book.core.model.Book;
import br.com.myshelf.backend.modules.catalog.book.data.BookRepository;
import br.com.myshelf.backend.modules.catalog.genre.core.model.Genre;
import br.com.myshelf.backend.modules.catalog.publisher.core.model.Publisher;
import br.com.myshelf.backend.modules.catalog.author.data.AuthorRepository;
import br.com.myshelf.backend.modules.catalog.genre.data.GenreRepository;
import br.com.myshelf.backend.modules.catalog.publisher.data.PublisherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    @Transactional
    public BookDTO createBook(BookRegisterDTO dto) {

        if (bookRepository.existsByCodeIgnoreCase(dto.code().trim())) {
            throw new ResourceAlreadyExistsException("Livro", "ISBN/código", dto.code());
        }

        UUID publisherId = dto.publisher().id();
        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new ResourceNotFoundException("Editora", publisherId));

        List<Author> validatedAuthors = new ArrayList<>();
        if (dto.authors() != null) {
            for (var authorDto : dto.authors()) {
                UUID authorId = authorDto.id();
                Author author = authorRepository.findById(authorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Autor", authorId));
                validatedAuthors.add(author);
            }
        }

        List<Genre> validatedGenres = new ArrayList<>();
        if (dto.genres() != null) {
            for (var genreDto : dto.genres()) {
                UUID genreId = genreDto.id();
                Genre genre = genreRepository.findById(genreId)
                        .orElseThrow(() -> new ResourceNotFoundException("Gênero", genreId));
                validatedGenres.add(genre);
            }
        }

        Book bookEntity = bookMapper.toEntity(dto, publisher, validatedAuthors, validatedGenres);
        bookEntity = bookRepository.save(bookEntity);

        return bookMapper.toResponseDTO(bookEntity);
    }
}
