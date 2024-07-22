package com.trinity.bookstore.mapper;

import com.trinity.bookstore.dto.BookDto;
import org.mapstruct.Mapper;

import com.trinity.bookstore.entity.BookEntity;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookEntity toBookEntity(BookDto request);
    BookDto toBookDto(BookEntity bookEntity);
}
