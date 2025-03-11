package com.od.mongo.controllers;

import com.od.mongo.model.AuthoredBook;
import com.od.mongo.model.CreateUpdateBookRequest;
import com.od.mongo.model.dtos.AuthoredBookDto;
import com.od.mongo.model.dtos.CreateUpdateBookRequestDto;
import com.od.mongo.mappers.Mapper;
import com.od.mongo.services.AuthoredBookService;
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
@RequestMapping(path = "/books")
public class AuthoredBooksController {
    private final AuthoredBookService authoredBookService;
    private final Mapper<AuthoredBook, AuthoredBookDto> bookMapper;
    private final Mapper<CreateUpdateBookRequest, CreateUpdateBookRequestDto> createUpdateRequestMapper;

    @GetMapping
    public List<AuthoredBookDto> listBooks() {
        // Ask the book service for all the books
        return authoredBookService.listBooks()
                .stream().map(bookMapper::mapTo)
                .toList();
    }

    @GetMapping(path="/{isbn}")
    public ResponseEntity<AuthoredBookDto> getBookByIsbn(@PathVariable final String isbn) {
        return authoredBookService.getBookByIsbn(isbn)
                .map(book ->
                        ResponseEntity.ok(bookMapper.mapTo(book))
                ).orElseGet(() ->
                        new ResponseEntity<>(HttpStatus.NOT_FOUND)
                );
    }

    @PutMapping(path = "/{isbn}")
    public AuthoredBookDto createUpdateBook(
            @PathVariable final String isbn,
            @RequestBody final CreateUpdateBookRequestDto requestBody
    ) {
        final CreateUpdateBookRequest createUpdateBookRequest = createUpdateRequestMapper.mapFrom(requestBody);

        final AuthoredBook createUpdatedBook = authoredBookService.createUpdateBook(isbn, createUpdateBookRequest);

        return bookMapper.mapTo(createUpdatedBook);
    }

    @DeleteMapping(path = "/{isbn}")
    public void deleteBook(@PathVariable final String isbn) {
        authoredBookService.deleteBook(isbn);
    }

}
