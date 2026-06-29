package br.com.myshelf.backend.application.mapper;

import br.com.myshelf.backend.application.dto.publisher.PublisherRegisterDTO;
import br.com.myshelf.backend.application.dto.publisher.ListPublisherDTO;
import br.com.myshelf.backend.application.dto.publisher.PublisherResponseDTO;
import br.com.myshelf.backend.domain.model.Publisher;
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
