package com.trinity.bookstore.service;

import com.trinity.bookstore.dto.request.BorrowingRequest;
import com.trinity.bookstore.dto.response.BorrowingResponse;

public interface IBorrowingService {
    BorrowingResponse borrowBook(BorrowingRequest request);
    BorrowingResponse returnBook(BorrowingRequest request);
}
