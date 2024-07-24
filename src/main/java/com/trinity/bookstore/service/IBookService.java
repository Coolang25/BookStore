package com.trinity.bookstore.service;

import java.util.List;

import com.trinity.bookstore.dto.request.BookCreationRequest;
import com.trinity.bookstore.dto.request.BookUpdateRequest;
import com.trinity.bookstore.dto.response.BookResponse;

public interface IBookService {
    BookResponse createBook(BookCreationRequest request);

    List<BookResponse> getAllBooks();

    BookResponse getBook(Long id);

    BookResponse updateBook(Long id, BookUpdateRequest request);

    String deleteBook(Long id);

}
