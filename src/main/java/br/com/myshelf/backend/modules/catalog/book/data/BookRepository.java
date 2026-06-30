package br.com.myshelf.backend.modules.catalog.book.data;

import br.com.myshelf.backend.modules.catalog.book.core.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    boolean existsByCodeIgnoreCase(String code);
}
