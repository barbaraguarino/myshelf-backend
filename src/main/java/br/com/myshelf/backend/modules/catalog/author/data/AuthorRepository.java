package br.com.myshelf.backend.modules.catalog.author.data;

import br.com.myshelf.backend.modules.catalog.author.core.model.Author;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    boolean existsByNameIgnoreCase(String name);
    List<Author> findByNameInIgnoreCase(List<String> names);
    @NonNull Page<Author> findAll(@NonNull Pageable pageable);
}
