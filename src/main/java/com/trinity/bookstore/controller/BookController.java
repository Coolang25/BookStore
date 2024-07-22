package com.trinity.bookstore.controller;

import com.trinity.bookstore.dto.BookDto;
import com.trinity.bookstore.dto.ApiResponse;
import com.trinity.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("books")
public class BookController {
    BookService bookService;

    @PostMapping
    ApiResponse<BookDto> createBook(@Valid @RequestBody BookDto request) {
        return ApiResponse.<BookDto>builder()
                .result(bookService.createBook(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<BookDto>> getAllBook() {
        return ApiResponse.<List<BookDto>>builder()
                .result(bookService.getAllBooks())
                .build();
    }

}
