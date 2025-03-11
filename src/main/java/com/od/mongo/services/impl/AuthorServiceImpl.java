package com.od.mongo.services.impl;

import com.od.mongo.model.CreateUpdateAuthorRequest;
import com.od.mongo.model.documents.Author;
import com.od.mongo.repositories.AuthorRepository;
import com.od.mongo.services.AuthorService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
  private final AuthorRepository authorRepository;

  @Override
  public List<Author> listAuthors() {
    return authorRepository.findAll();
  }

  @Override
  public Optional<Author> getAuthorById(final String id) {
    return authorRepository.findById(id);
  }

  @Override
  public Author createUpdateAuthor(String authorId,
      CreateUpdateAuthorRequest createUpdateAuthorRequest) {
    return authorRepository.findById(authorId).map(
        existingAuthor -> {
        final Author author = Author.builder()
            .id(authorId)
            .givenName(createUpdateAuthorRequest.getGivenName())
            .familyName(createUpdateAuthorRequest.getFamilyName())
            .description(createUpdateAuthorRequest.getDescription())
            .created(existingAuthor.getCreated())
            .lastUpdated(LocalDateTime.now())
            .build();
        return authorRepository.save(author);
        }).orElseGet(() -> {
        final LocalDateTime now = LocalDateTime.now();
        final Author newAuthor = Author.builder()
            .id(authorId)
            .givenName(createUpdateAuthorRequest.getGivenName())
            .familyName(createUpdateAuthorRequest.getFamilyName())
            .description(createUpdateAuthorRequest.getDescription())
            .created(now)
            .lastUpdated(now)
            .build();
        return authorRepository.save(newAuthor);
        });
  }

  @Override
  public void deleteAuthor(final String id) {
    authorRepository.deleteById(id);
  }
}
