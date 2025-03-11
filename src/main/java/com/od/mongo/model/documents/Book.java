package com.od.mongo.model.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    private String isbn;
    private String title;
    private LocalDate datePublished;
    private String authorId;
    private LocalDateTime created;
    private LocalDateTime lastUpdated;
}
