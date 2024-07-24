package com.trinity.bookstore.service;

import java.util.List;
import java.util.Set;

import com.trinity.bookstore.dto.request.AuthorCreationRequest;
import com.trinity.bookstore.dto.request.AuthorUpdateRequest;
import com.trinity.bookstore.dto.request.BookUpdateRequest;
import com.trinity.bookstore.dto.response.AuthorResponse;
import com.trinity.bookstore.dto.response.BookResponse;

public interface IAuthorService {
    AuthorResponse createAuthor(AuthorCreationRequest request);
    List<AuthorResponse> getAllAuthors();

    Set<AuthorResponse> getAllAuthorsOwnBooks();

    AuthorResponse getAuthor(Long id);

    AuthorResponse updateAuthor(Long id, AuthorUpdateRequest request);
}
