package com.trinity.bookstore.api;

import java.util.List;
import java.util.Set;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.trinity.bookstore.dto.request.AuthorCreationRequest;
import com.trinity.bookstore.dto.request.AuthorUpdateRequest;
import com.trinity.bookstore.dto.response.ApiResponse;
import com.trinity.bookstore.dto.response.AuthorResponse;
import com.trinity.bookstore.service.IAuthorService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("authors")
public class AuthorRestController {
    IAuthorService iAuthorService;

    @PostMapping
    ApiResponse<AuthorResponse> createAuthor(@Valid @RequestBody AuthorCreationRequest request) {
        return ApiResponse.<AuthorResponse>builder()
                .result(iAuthorService.createAuthor(request))
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<AuthorResponse>> getAllAuthor() {
        return ApiResponse.<List<AuthorResponse>>builder()
                .result(iAuthorService.getAllAuthors())
                .build();
    }

    @GetMapping("")
    ApiResponse<Set<AuthorResponse>> getAllAuthorsHasBook() {
        return ApiResponse.<Set<AuthorResponse>>builder()
                .result(iAuthorService.getAllAuthorsOwnBooks())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<AuthorResponse> getAuthor(@PathVariable Long id) {
        return ApiResponse.<AuthorResponse>builder()
                .result(iAuthorService.getAuthor(id))
                .build();
    }

    @PostMapping("/{id}")
    ApiResponse<AuthorResponse> updateAuthor(@PathVariable Long id, @RequestBody @Valid AuthorUpdateRequest request) {
        return ApiResponse.<AuthorResponse>builder()
                .result(iAuthorService.updateAuthor(id, request))
                .build();
    }
}
