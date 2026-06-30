package br.com.myshelf.backend.modules.catalog.author.core.service;

import br.com.myshelf.backend.core.exception.domain.ResourceAlreadyExistsException;
import br.com.myshelf.backend.core.exception.domain.ResourceNotFoundException;
import br.com.myshelf.backend.modules.catalog.author.api.dto.AuthorRegisterDTO;
import br.com.myshelf.backend.modules.catalog.author.api.dto.AuthorResponseDTO;
import br.com.myshelf.backend.modules.catalog.author.api.dto.ListAuthorDTO;
import br.com.myshelf.backend.modules.catalog.author.api.dto.ListAuthorRegisterDTO;
import br.com.myshelf.backend.modules.catalog.author.api.mapper.AuthorMapper;
import br.com.myshelf.backend.modules.catalog.author.core.model.Author;
import br.com.myshelf.backend.modules.catalog.author.data.AuthorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorResponseDTO createAuthor(AuthorRegisterDTO authorRegisterDTO){
        if(authorRepository.existsByNameIgnoreCase(authorRegisterDTO.name().trim()))
            throw new ResourceAlreadyExistsException("Autor", "nome", authorRegisterDTO.name().trim());

        Author author = authorMapper.toEntity(authorRegisterDTO);
        author = authorRepository.save(author);

        return authorMapper.toResponseDTO(author);
    }

    public ListAuthorDTO createAuthorList(ListAuthorRegisterDTO listAuthorRegisterDTO) {
        List<String> names = listAuthorRegisterDTO.authors().stream()
                .map(dto -> dto.name().trim())
                .toList();

        List<Author> existingAuthors = authorRepository.findByNameInIgnoreCase(names);

        Map<String, Author> existingAuthorsMap = existingAuthors.stream()
                .collect(Collectors.toMap(
                        author -> author.getName().trim().toLowerCase(),
                        author -> author
                ));

        List<Author> allAuthorsReturn = new ArrayList<>();
        List<Author> newAuthors = new ArrayList<>();

        for (String name : names) {
            String nameKey = name.toLowerCase();

            if (existingAuthorsMap.containsKey(nameKey)) {
                allAuthorsReturn.add(existingAuthorsMap.get(nameKey));
            } else {
                Author newAuthor = Author.createAuthor(name);
                newAuthors.add(newAuthor);
                existingAuthorsMap.put(nameKey, newAuthor);
            }
        }

        if (!newAuthors.isEmpty()) {
            List<Author> savedAuthors = authorRepository.saveAll(newAuthors);
            allAuthorsReturn.addAll(savedAuthors);
        }

        return authorMapper.toResponseDTO(allAuthorsReturn);
    }

    public Author findAuthorById(UUID authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Autor", authorId));
    }
}
