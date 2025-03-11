package com.od.mongo.repositories;

import com.od.mongo.model.documents.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
