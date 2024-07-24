package com.trinity.bookstore.api;

import java.util.List;

import jakarta.validation.Valid;

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
@RequestMapping("books")
public class BookRestController {
    IBookService iBookService;

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

    @PutMapping("/{id}")
    ApiResponse<BookResponse> updateBook(@PathVariable Long id, @RequestBody @Valid BookUpdateRequest request) {
        return ApiResponse.<BookResponse>builder()
                .result(iBookService.updateBook(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteBook(@PathVariable Long id) {
        return ApiResponse.<String>builder().result(iBookService.deleteBook(id)).build();
    }
}
