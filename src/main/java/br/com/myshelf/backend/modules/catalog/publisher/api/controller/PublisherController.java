package br.com.myshelf.backend.modules.catalog.publisher.api.controller;

import br.com.myshelf.backend.modules.catalog.publisher.core.service.PublisherService;
import br.com.myshelf.backend.modules.catalog.publisher.api.dto.ListPublisherResponseDTO;
import br.com.myshelf.backend.modules.catalog.publisher.api.dto.ListPublisherRegisterDTO;
import br.com.myshelf.backend.modules.catalog.publisher.api.dto.PublisherRegisterDTO;
import br.com.myshelf.backend.modules.catalog.publisher.api.dto.PublisherResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    public ResponseEntity<PublisherResponseDTO> createPublisher(@Valid @RequestBody PublisherRegisterDTO publisherRegisterDTO) {
        var publisher = publisherService.createPublisher(publisherRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(publisher);
    }

    @PostMapping("/list")
    public ResponseEntity<ListPublisherResponseDTO> createPublisherList(@Valid @RequestBody ListPublisherRegisterDTO listPublisherRegisterDTO){
        var publisherList = publisherService.createPublisherList(listPublisherRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherList);
    }
}
