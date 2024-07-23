package com.trinity.bookstore.api;

import java.util.Set;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.trinity.bookstore.dto.ApiResponse;
import com.trinity.bookstore.dto.AuthorDto;
import com.trinity.bookstore.service.AuthorService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("authors")
public class AuthorRestController {
    AuthorService authorService;

    @PostMapping
    ApiResponse<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto request) {
        return ApiResponse.<AuthorDto>builder()
                .result(authorService.createAuthor(request))
                .build();
    }

    //    @GetMapping
    //    ApiResponse<List<AuthorDto>> getAllAuthor() {
    //        return ApiResponse.<List<AuthorDto>>builder()
    //                .result(authorService.getAllAuthors())
    //                .build();
    //    }

    @GetMapping("")
    ApiResponse<Set<AuthorDto>> getAllAuthorsHasBook() {
        return ApiResponse.<Set<AuthorDto>>builder()
                .result(authorService.getAllAuthorsOwnBooks())
                .build();
    }
}
