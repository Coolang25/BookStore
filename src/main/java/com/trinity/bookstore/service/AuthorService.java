package com.trinity.bookstore.service;

import java.util.List;
import java.util.Set;

import com.trinity.bookstore.dto.AuthorDto;

public interface AuthorService {
    AuthorDto createAuthor(AuthorDto request);
    List<AuthorDto> getAllAuthors();

    Set<AuthorDto> getAllAuthorsHasBook();

}
