package br.com.myshelf.backend.domain.repository;

import br.com.myshelf.backend.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {}
