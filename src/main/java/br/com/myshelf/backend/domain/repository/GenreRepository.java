package br.com.myshelf.backend.domain.repository;

import br.com.myshelf.backend.domain.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID> {
    boolean existsByNameIgnoreCase(String name);
    Genre findByNameIgnoreCase(String name);
}
