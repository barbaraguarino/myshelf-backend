package br.com.myshelf.backend.domain.repository;

import br.com.myshelf.backend.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    boolean existsByNameIgnoreCase(String name);
    List<Author> findByNameInIgnoreCase(List<String> names);
}
