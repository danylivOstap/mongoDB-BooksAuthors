package com.od.mongo.services;

import com.od.mongo.model.CreateUpdateAuthorRequest;
import com.od.mongo.model.documents.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
  List<Author> listAuthors();

  Optional<Author> getAuthorById(String id);

  Author createUpdateAuthor(String authorId, CreateUpdateAuthorRequest createUpdateAuthorRequest);

  void deleteAuthor(String id);
}
