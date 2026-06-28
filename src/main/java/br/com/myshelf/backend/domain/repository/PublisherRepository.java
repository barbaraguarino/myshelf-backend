package br.com.myshelf.backend.domain.repository;

import br.com.myshelf.backend.domain.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID> {
    boolean existsByNameIgnoreCase(String name);
    List<Publisher> findByNameInIgnoreCase(List<String> names);
}
