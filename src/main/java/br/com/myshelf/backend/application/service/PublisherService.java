package br.com.myshelf.backend.application.service;

import br.com.myshelf.backend.application.dto.publisher.PublisherRegisterDTO;
import br.com.myshelf.backend.application.dto.publisher.ListPublisherRegisterDTO;
import br.com.myshelf.backend.application.dto.publisher.ListPublisherDTO;
import br.com.myshelf.backend.application.dto.publisher.PublisherDTO;
import br.com.myshelf.backend.application.mapper.PublisherMapper;
import br.com.myshelf.backend.domain.exception.ResourceAlreadyExistsException;
import br.com.myshelf.backend.domain.model.Publisher;
import br.com.myshelf.backend.domain.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherDTO createPublisher(PublisherRegisterDTO publisherRegisterDTO){
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
