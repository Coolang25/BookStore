package com.trinity.bookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.trinity.bookstore.dto.request.AuthorCreationRequest;
import com.trinity.bookstore.dto.request.AuthorUpdateRequest;
import com.trinity.bookstore.dto.response.AuthorResponse;
import com.trinity.bookstore.entity.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorCreationRequest request);

    AuthorResponse toAuthorResponse(Author author);

    void updateAuthor(@MappingTarget Author author, AuthorUpdateRequest request);
}
