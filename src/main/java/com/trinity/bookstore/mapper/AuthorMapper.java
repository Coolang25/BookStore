package com.trinity.bookstore.mapper;

import com.trinity.bookstore.dto.request.AuthorUpdateRequest;
import com.trinity.bookstore.dto.response.AuthorResponse;
import org.mapstruct.Mapper;

import com.trinity.bookstore.dto.request.AuthorCreationRequest;
import com.trinity.bookstore.entity.Author;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorCreationRequest request);
    AuthorResponse toAuthorResponse(Author author);

    void updateAuthor(@MappingTarget Author author, AuthorUpdateRequest request);
}
