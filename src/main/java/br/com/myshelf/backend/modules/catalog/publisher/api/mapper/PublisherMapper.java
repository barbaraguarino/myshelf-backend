package br.com.myshelf.backend.modules.catalog.publisher.api.mapper;

import br.com.myshelf.backend.modules.catalog.publisher.api.dto.ListPublisherDTO;
import br.com.myshelf.backend.modules.catalog.publisher.api.dto.PublisherRegisterDTO;
import br.com.myshelf.backend.modules.catalog.publisher.api.dto.PublisherResponseDTO;
import br.com.myshelf.backend.modules.catalog.publisher.core.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublisherMapper {

    public Publisher toEntity(PublisherRegisterDTO dto) {
        if (dto == null) return null;
        return Publisher.createPublisher(dto.name());
    }

    public PublisherResponseDTO toResponseDTO(Publisher publisher) {
        if (publisher == null) return null;
        return new PublisherResponseDTO(publisher.getId(), publisher.getName());
    }

    public ListPublisherDTO toResponseDTO(List<Publisher> publishers) {
        if (publishers == null) return new ListPublisherDTO(Collections.emptyList());

        List<PublisherResponseDTO> list = publishers.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

        return new ListPublisherDTO(list);
    }
}
