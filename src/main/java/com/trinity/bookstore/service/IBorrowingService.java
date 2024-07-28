package com.trinity.bookstore.service;

import com.trinity.bookstore.dto.request.BorrowingRequest;
import com.trinity.bookstore.dto.response.BookResponse;
import com.trinity.bookstore.dto.response.BorrowingResponse;

import java.util.List;

public interface IBorrowingService {
    BorrowingResponse borrowBook(BorrowingRequest request);
    BorrowingResponse returnBook(BorrowingRequest request);

    List<BookResponse> unReturnedBooks();
}
