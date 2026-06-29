package br.com.myshelf.backend.application.mapper;

import br.com.myshelf.backend.application.dto.book.BookRegisterDTO;
import br.com.myshelf.backend.domain.model.Author;
import br.com.myshelf.backend.domain.model.Book;
import br.com.myshelf.backend.domain.model.Genre;
import br.com.myshelf.backend.domain.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BookMapper{

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
}
