package com.od.mongo.services;

import com.od.mongo.model.AuthoredBook;
import com.od.mongo.model.CreateUpdateBookRequest;
import com.od.mongo.model.documents.Book;
import java.util.List;
import java.util.Optional;

public interface AuthoredBookService {
  List<AuthoredBook> listBooks();

  Optional<AuthoredBook> getBookByIsbn(String isbn);

  AuthoredBook createUpdateBook(String isbn, CreateUpdateBookRequest createUpdateBookRequest);

  void deleteBook(String isbn);
}
