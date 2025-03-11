package com.od.mongo.services.impl;

import com.od.mongo.exceptions.AuthorNotFoundException;
import com.od.mongo.model.AuthoredBook;
import com.od.mongo.model.CreateUpdateBookRequest;
import com.od.mongo.model.documents.Author;
import com.od.mongo.model.documents.Book;
import com.od.mongo.repositories.AuthorRepository;
import com.od.mongo.repositories.BookRepository;
import com.od.mongo.services.AuthoredBookService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthoredBookServiceImpl implements AuthoredBookService {
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  @Override
  public List<AuthoredBook> listBooks() {
    final List<Book> allBooks = bookRepository.findAll();
    final List<String> authorIds = allBooks.stream().map(Book::getAuthorId).toList();
    final List<Author> authors = authorRepository.findAllById(authorIds);

    final Map<String, Author> authorIndex = authors
        .stream()
        .collect(Collectors.toMap(
            Author::getId,
            author -> author));

    return allBooks.stream().map(book -> {
      final String authorId = book.getAuthorId();
      final Author author = authorIndex.get(authorId);
      if (null == author) {
        throw new AuthorNotFoundException(authorId);
      }
      return buildAuthoredBook(book, author);
    }).toList();
  }

  @Override
  public Optional<AuthoredBook> getBookByIsbn(String isbn) {
    return bookRepository.findById(isbn)
        .map(book -> {
          final String authorId = book.getAuthorId();
          final Author author = authorRepository.findById(authorId).orElseThrow(() ->
                  new AuthorNotFoundException(authorId));

          return buildAuthoredBook(book, author);
        });
  }

  private AuthoredBook buildAuthoredBook(final Book book, final Author author) {
    return AuthoredBook.builder()
        .isbn(book.getIsbn())
        .title(book.getTitle())
        .datePublished(book.getDatePublished())
        .author(author)
        .created(book.getCreated())
        .lastUpdated(book.getLastUpdated())
        .build();
  }

  @Override
  public AuthoredBook createUpdateBook(final String isbn,
      final CreateUpdateBookRequest createUpdateBookRequest) {
    final String authorId = createUpdateBookRequest.getAuthorId();

    final Optional<Author> existingAuthor = authorRepository.findById(authorId);
    if (existingAuthor.isEmpty()) {
      throw new AuthorNotFoundException(authorId);
    }

    final Book book = bookRepository.findById(isbn).map(existingBook -> {
      // Update the existing Book
      final Book updatedBook = Book.builder()
          .isbn(isbn)
          .title(createUpdateBookRequest.getTitle())
          .datePublished(createUpdateBookRequest.getDatePublished())
          .authorId(authorId)
          .created(existingBook.getCreated())
          .lastUpdated(LocalDateTime.now())
          .build();
      return bookRepository.save(updatedBook);
    }).orElseGet(() -> {
      final LocalDateTime now = LocalDateTime.now();
      final Book newBook = Book.builder()
          .isbn(isbn)
          .title(createUpdateBookRequest.getTitle())
          .datePublished(createUpdateBookRequest.getDatePublished())
          .authorId(authorId)
          .created(now)
          .lastUpdated(now)
          .build();
      return bookRepository.save(newBook);
    });

    return buildAuthoredBook(book, existingAuthor.get());
  }

  @Override
  public void deleteBook(final String isbn) {
    bookRepository.deleteById(isbn);
  }
}
