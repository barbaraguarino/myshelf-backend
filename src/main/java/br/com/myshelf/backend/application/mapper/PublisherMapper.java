package br.com.myshelf.backend.application.mapper;

import br.com.myshelf.backend.application.dto.publisher.PublisherAddDTO;
import br.com.myshelf.backend.application.dto.publisher.PublisherListAddDTO;
import br.com.myshelf.backend.application.dto.publisher.PublisherListResponseDTO;
import br.com.myshelf.backend.application.dto.publisher.PublisherResponseDTO;
import br.com.myshelf.backend.domain.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublisherMapper {

    public Publisher toEntity(PublisherAddDTO dto) {
        if (dto == null) return null;
        return Publisher.createPublisher(dto.name());
    }

    public List<Publisher> toEntity(PublisherListAddDTO dto) {
        if (dto == null || dto.publishers() == null) return Collections.emptyList();

        return dto.publishers().stream()
                .map(publisher -> Publisher.createPublisher(publisher.name()))
                .collect(Collectors.toList());
    }

    public PublisherResponseDTO toResponseDTO(Publisher publisher) {
        if (publisher == null) return null;
        return new PublisherResponseDTO(publisher.getId(), publisher.getName());
    }

    public PublisherListResponseDTO toResponseDTO(List<Publisher> publishers) {
        if (publishers == null) return new PublisherListResponseDTO(Collections.emptyList());

        List<PublisherResponseDTO> list = publishers.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

        return new PublisherListResponseDTO(list);
    }
}
