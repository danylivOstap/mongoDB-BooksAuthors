package com.od.mongo.controllers;

import com.od.mongo.model.CreateUpdateAuthorRequest;
import com.od.mongo.model.documents.Author;
import com.od.mongo.model.dtos.AuthorDto;
import com.od.mongo.model.dtos.CreateUpdateAuthorRequestDto;
import com.od.mongo.mappers.Mapper;
import com.od.mongo.services.AuthorService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/authors")
public class AuthorsController {
    private final AuthorService authorService;
    private final Mapper<Author, AuthorDto> authorMapper;
    private final Mapper<CreateUpdateAuthorRequest, CreateUpdateAuthorRequestDto> createUpdateRequestMapper;

    @GetMapping
    public List<AuthorDto> listAuthors() {
        return authorService.listAuthors()
                .stream()
                .map(authorMapper::mapTo)
                .toList();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable final String id) {
        return authorService.getAuthorById(id)
                .map(author -> ResponseEntity.ok(authorMapper.mapTo(author))
                )
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
                );
    }

    @PutMapping(path = "/{id}")
    public AuthorDto createUpdateAuthor(
            @PathVariable final String id,
            @RequestBody final CreateUpdateAuthorRequestDto requestBody
    ) {
        final CreateUpdateAuthorRequest createUpdateAuthorRequest = createUpdateRequestMapper.mapFrom(requestBody);

        final Author createUpdatedAuthor = authorService.createUpdateAuthor(id, createUpdateAuthorRequest);

        return authorMapper.mapTo(createUpdatedAuthor);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAuthor(@PathVariable final String id) {
        authorService.deleteAuthor(id);
    }
}
