package br.com.myshelf.backend.infrastructure.web.controller;

import br.com.myshelf.backend.application.dto.publisher.PublisherRegisterDTO;
import br.com.myshelf.backend.application.dto.publisher.ListPublisherRegisterDTO;
import br.com.myshelf.backend.application.dto.publisher.ListPublisherDTO;
import br.com.myshelf.backend.application.dto.publisher.PublisherResponseDTO;
import br.com.myshelf.backend.application.service.PublisherService;
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
    public ResponseEntity<ListPublisherDTO> createPublisherList(@Valid @RequestBody ListPublisherRegisterDTO listPublisherRegisterDTO){
        var publisherList = publisherService.createPublisherList(listPublisherRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherList);
    }
}
