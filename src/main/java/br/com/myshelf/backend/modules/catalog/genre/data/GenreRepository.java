package br.com.myshelf.backend.modules.catalog.genre.data;

import br.com.myshelf.backend.modules.catalog.genre.core.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID> {
    boolean existsByNameIgnoreCase(String name);
    List<Genre> findByNameInIgnoreCase(List<String> names);
}
