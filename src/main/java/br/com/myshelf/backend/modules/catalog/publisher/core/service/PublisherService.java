package br.com.myshelf.backend.modules.catalog.publisher.core.service;

import br.com.myshelf.backend.core.exception.domain.ResourceAlreadyExistsException;
import br.com.myshelf.backend.modules.catalog.publisher.api.dto.ListPublisherDTO;
import br.com.myshelf.backend.modules.catalog.publisher.api.dto.ListPublisherRegisterDTO;
import br.com.myshelf.backend.modules.catalog.publisher.api.dto.PublisherRegisterDTO;
import br.com.myshelf.backend.modules.catalog.publisher.api.dto.PublisherResponseDTO;
import br.com.myshelf.backend.modules.catalog.publisher.api.mapper.PublisherMapper;
import br.com.myshelf.backend.modules.catalog.publisher.core.model.Publisher;
import br.com.myshelf.backend.modules.catalog.publisher.data.PublisherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherResponseDTO createPublisher(PublisherRegisterDTO publisherRegisterDTO){
        if(publisherRepository.existsByNameIgnoreCase(publisherRegisterDTO.name().trim()))
            throw new ResourceAlreadyExistsException("Editora", "nome", publisherRegisterDTO.name().trim());

        Publisher publisher = publisherMapper.toEntity(publisherRegisterDTO);
        publisher = publisherRepository.save(publisher);

        return publisherMapper.toResponseDTO(publisher);
    }

    public ListPublisherDTO createPublisherList(ListPublisherRegisterDTO listPublisherRegisterDTO) {
        List<String> names = listPublisherRegisterDTO.publishers().stream()
                .map(dto -> dto.name().trim())
                .toList();

        List<Publisher> existingPublishers = publisherRepository.findByNameInIgnoreCase(names);

        Map<String, Publisher> existingPublishersMap = existingPublishers.stream()
                .collect(Collectors.toMap(
                        publisher -> publisher.getName().trim().toLowerCase(),
                        publisher -> publisher
                ));

        List<Publisher> allPublishersReturn = new ArrayList<>();
        List<Publisher> newPublishers = new ArrayList<>();

        for (String name : names) {
            String nameKey = name.toLowerCase();

            if (existingPublishersMap.containsKey(nameKey)) {
                allPublishersReturn.add(existingPublishersMap.get(nameKey));
            } else {
                Publisher newPublisher = Publisher.createPublisher(name);
                newPublishers.add(newPublisher);

                existingPublishersMap.put(nameKey, newPublisher);
            }
        }

        if (!newPublishers.isEmpty()) {
            List<Publisher> savedPublishers = publisherRepository.saveAll(newPublishers);
            allPublishersReturn.addAll(savedPublishers);
        }

        return publisherMapper.toResponseDTO(allPublishersReturn);
    }
}
