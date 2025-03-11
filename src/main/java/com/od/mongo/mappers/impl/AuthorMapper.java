package com.od.mongo.mappers.impl;

import com.od.mongo.model.documents.Author;
import com.od.mongo.model.dtos.AuthorDto;
import com.od.mongo.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<Author, AuthorDto> {
    @Override
    public AuthorDto mapTo(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .givenName(author.getGivenName())
                .familyName(author.getFamilyName())
                .description(author.getDescription())
                .build();
    }

    @Override
    public Author mapFrom(AuthorDto authorDto) {
        return Author.builder()
                .id(authorDto.getId())
                .givenName(authorDto.getGivenName())
                .familyName(authorDto.getFamilyName())
                .description(authorDto.getDescription())
                .build();
    }
}
