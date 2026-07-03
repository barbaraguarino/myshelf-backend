package br.com.myshelf.backend.modules.catalog.publisher.data;

import br.com.myshelf.backend.modules.catalog.publisher.core.model.Publisher;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID> {
    boolean existsByNameIgnoreCase(String name);
    List<Publisher> findByNameInIgnoreCase(List<String> names);
    @NonNull Page<Publisher> findAll(@NonNull Pageable pageable);
}
