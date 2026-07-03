package br.com.myshelf.backend.modules.catalog.book.data;

import br.com.myshelf.backend.modules.catalog.book.core.model.Book;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    boolean existsByCodeIgnoreCase(String code);
    @NonNull Page<Book> findAll(@NonNull Pageable pageable);
}
