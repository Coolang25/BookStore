package com.trinity.bookstore.mapper;

import com.trinity.bookstore.dto.request.AuthorUpdateRequest;
import com.trinity.bookstore.dto.request.BookUpdateRequest;
import com.trinity.bookstore.dto.response.BookResponse;
import com.trinity.bookstore.entity.Author;
import org.mapstruct.Mapper;

import com.trinity.bookstore.dto.request.BookCreationRequest;
import com.trinity.bookstore.entity.Book;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toBook(BookCreationRequest request);
    BookResponse toBookResponse(Book book);
    void updateBook(@MappingTarget Book book, BookUpdateRequest request);
}
