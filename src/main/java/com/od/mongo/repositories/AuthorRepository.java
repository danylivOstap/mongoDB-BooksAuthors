package com.od.mongo.repositories;

import com.od.mongo.model.documents.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
