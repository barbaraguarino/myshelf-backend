package br.com.myshelf.backend.modules.catalog.book.api.mapper;

import br.com.myshelf.backend.modules.catalog.book.api.dto.BookDTO;
import br.com.myshelf.backend.modules.catalog.book.api.dto.BookRegisterDTO;
import br.com.myshelf.backend.modules.catalog.book.core.model.Book;
import br.com.myshelf.backend.modules.catalog.genre.api.mapper.GenreMapper;
import br.com.myshelf.backend.modules.catalog.publisher.api.mapper.PublisherMapper;
import br.com.myshelf.backend.modules.catalog.author.core.model.Author;
import br.com.myshelf.backend.modules.catalog.genre.core.model.Genre;
import br.com.myshelf.backend.modules.catalog.publisher.core.model.Publisher;
import br.com.myshelf.backend.modules.catalog.author.api.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final PublisherMapper publisherMapper;
    private final AuthorMapper authorMapper;
    private final GenreMapper genreMapper;

    public Book toEntity(BookRegisterDTO dto,
                         Publisher publisher,
                         List<Author> authors,
                         List<Genre> genres) {

        Set<Author> authorSet = (authors != null) ? new HashSet<>(authors) : new HashSet<>();
        Set<Genre> genreSet = (genres != null) ? new HashSet<>(genres) : new HashSet<>();

        return Book.createBook(
                dto.code(),
                dto.title(),
                dto.format(),
                dto.pages(),
                dto.edition(),
                dto.summary(),
                dto.language(),
                dto.publicationYear(),
                publisher,
                authorSet,
                genreSet);
    }

    public BookDTO toResponseDTO(Book book) {
        if (book == null) {
            return null;
        }

        return new BookDTO(
                book.getId(),
                book.getCode(),
                book.getTitle(),
                book.getFormat(),
                book.getPages(),
                book.getEdition(),
                book.getSummary(),
                book.getLanguage(),
                book.getPublicationYear(),
                publisherMapper.toResponseDTO(book.getPublisher()),
                authorMapper.toResponseDTO(book.getAuthors().stream().toList()).authors(),
                genreMapper.toResponseDTO(book.getGenres().stream().toList()).genres()
        );
    }
}
