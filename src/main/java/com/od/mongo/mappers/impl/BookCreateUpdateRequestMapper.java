package com.od.mongo.mappers.impl;

import com.od.mongo.model.CreateUpdateBookRequest;
import com.od.mongo.model.dtos.CreateUpdateBookRequestDto;
import com.od.mongo.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class BookCreateUpdateRequestMapper
    implements Mapper<CreateUpdateBookRequest, CreateUpdateBookRequestDto> {

    @Override
    public CreateUpdateBookRequestDto mapTo(final CreateUpdateBookRequest createUpdateBookRequest) {
        return CreateUpdateBookRequestDto.builder()
                .title(createUpdateBookRequest.getTitle())
                .authorId(createUpdateBookRequest.getAuthorId())
                .datePublished(createUpdateBookRequest.getDatePublished())
                .build();
    }

    @Override
    public CreateUpdateBookRequest mapFrom(final CreateUpdateBookRequestDto createUpdateBookRequestDto) {
        return CreateUpdateBookRequest.builder()
                .title(createUpdateBookRequestDto.getTitle())
                .authorId(createUpdateBookRequestDto.getAuthorId())
                .datePublished(createUpdateBookRequestDto.getDatePublished())
                .build();
    }
}
