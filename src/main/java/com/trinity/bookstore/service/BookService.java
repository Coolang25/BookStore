package com.trinity.bookstore.service;

import java.util.List;

import com.trinity.bookstore.dto.BookDto;

public interface BookService {
    BookDto createBook(BookDto request);

    List<BookDto> getAllBooks();
}
