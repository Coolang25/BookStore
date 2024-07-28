package com.trinity.bookstore.api;

import java.util.List;

import com.trinity.bookstore.dto.request.BorrowingRequest;
import com.trinity.bookstore.dto.response.BorrowingResponse;
import com.trinity.bookstore.service.IBorrowingService;
import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.trinity.bookstore.dto.request.BookCreationRequest;
import com.trinity.bookstore.dto.request.BookUpdateRequest;
import com.trinity.bookstore.dto.response.ApiResponse;
import com.trinity.bookstore.dto.response.BookResponse;
import com.trinity.bookstore.service.IBookService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/books")
public class BookRestController {
    IBookService iBookService;
    IBorrowingService borrowingService;

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping
    ApiResponse<BookResponse> createBook(@Valid @RequestBody BookCreationRequest request) {
        return ApiResponse.<BookResponse>builder()
                .result(iBookService.createBook(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<BookResponse>> getAllBook() {
        return ApiResponse.<List<BookResponse>>builder()
                .result(iBookService.getAllBooks())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<BookResponse> getBook(@PathVariable Long id) {
        return ApiResponse.<BookResponse>builder()
                .result(iBookService.getBook(id))
                .build();
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PutMapping("/{id}")
    ApiResponse<BookResponse> updateBook(@PathVariable Long id, @RequestBody @Valid BookUpdateRequest request) {
        return ApiResponse.<BookResponse>builder()
                .result(iBookService.updateBook(id, request))
                .build();
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @DeleteMapping("/{id}")
    ApiResponse<String> deleteBook(@PathVariable Long id) {
        return ApiResponse.<String>builder().result(iBookService.deleteBook(id)).build();
    }

    @PostMapping("/borrow")
    public ApiResponse<BorrowingResponse> borrowBook(@RequestBody BorrowingRequest request) {
        return ApiResponse.<BorrowingResponse>builder()
                .result(borrowingService.borrowBook(request))
                .build();
    }

    @PutMapping("/return")
    public ApiResponse<BorrowingResponse> returnBook(@RequestBody BorrowingRequest request) {
        return ApiResponse.<BorrowingResponse>builder()
                .result(borrowingService.returnBook(request))
                .build();
    }

    @GetMapping ("/unreturned")
    public ApiResponse<List<BookResponse>> returnBook() {
        return ApiResponse.<List<BookResponse>>builder()
                .result(borrowingService.unReturnedBooks())
                .build();
    }
}
