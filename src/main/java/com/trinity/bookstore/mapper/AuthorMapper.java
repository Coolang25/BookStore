package com.trinity.bookstore.mapper;

import com.trinity.bookstore.dto.AuthorDto;
import org.mapstruct.Mapper;

import com.trinity.bookstore.entity.AuthorEntity;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorEntity toAuthorEntity(AuthorDto request);

    AuthorDto toAuthorDto(AuthorEntity authorEntity);
}
