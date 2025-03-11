package com.od.mongo.mappers.impl;

import com.od.mongo.model.CreateUpdateAuthorRequest;
import com.od.mongo.model.dtos.CreateUpdateAuthorRequestDto;
import com.od.mongo.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorCreateUpdateRequestMapper
    implements Mapper<CreateUpdateAuthorRequest, CreateUpdateAuthorRequestDto> {
    @Override
    public CreateUpdateAuthorRequestDto mapTo(CreateUpdateAuthorRequest createUpdateAuthorRequest) {
        return CreateUpdateAuthorRequestDto.builder()
                .givenName(createUpdateAuthorRequest.getGivenName())
                .familyName(createUpdateAuthorRequest.getFamilyName())
                .description(createUpdateAuthorRequest.getDescription())
                .build();
    }

    @Override
    public CreateUpdateAuthorRequest mapFrom(
                CreateUpdateAuthorRequestDto createUpdateAuthorRequestDto) {
        return CreateUpdateAuthorRequest.builder()
                .givenName(createUpdateAuthorRequestDto.getGivenName())
                .familyName(createUpdateAuthorRequestDto.getFamilyName())
                .description(createUpdateAuthorRequestDto.getDescription())
                .build();
    }
}
