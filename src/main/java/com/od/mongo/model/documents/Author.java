package com.od.mongo.model.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    @Id
    private String id;
    private String givenName;
    private String familyName;
    private String description;
    private LocalDateTime created;
    private LocalDateTime lastUpdated;
}
