package com.trinity.bookstore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.trinity.bookstore.dto.request.AuthorCreationRequest;
import com.trinity.bookstore.dto.request.AuthorUpdateRequest;
import com.trinity.bookstore.dto.response.AuthorResponse;
import com.trinity.bookstore.entity.Author;
import com.trinity.bookstore.entity.Book;
import com.trinity.bookstore.exception.AppException;
import com.trinity.bookstore.exception.ErrorCode;
import com.trinity.bookstore.mapper.AuthorMapper;
import com.trinity.bookstore.repository.AuthorRepository;
import com.trinity.bookstore.repository.BookRepository;
import com.trinity.bookstore.service.IAuthorService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorService implements IAuthorService {
    AuthorRepository authorRepository;
    BookRepository bookRepository;
    AuthorMapper authorMapper;

    @Override
    public AuthorResponse createAuthor(AuthorCreationRequest request) {
        Optional<Author> author = authorRepository.findByFullNameAndDob(request.getFullName(), request.getDob());
        if (author.isPresent()) {
            throw new AppException(ErrorCode.AUTHOR_EXISTED);
        }

        return authorMapper.toAuthorResponse(authorRepository.save(authorMapper.toAuthor(request)));
    }

    @Override
    public List<AuthorResponse> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        return authors.stream().map(authorMapper::toAuthorResponse).collect(Collectors.toList());
    }

    @Override
    public Set<AuthorResponse> getAllAuthorsOwnBooks() {
        Set<Author> authors =
                bookRepository.findAll().stream().map(Book::getAuthor).collect(Collectors.toSet());
        return authors.stream().map(authorMapper::toAuthorResponse).collect(Collectors.toSet());
    }

    @Override
    public AuthorResponse getAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.AUTHOR_NOT_EXISTED));
        return authorMapper.toAuthorResponse(author);
    }

    @Override
    public AuthorResponse updateAuthor(Long id, AuthorUpdateRequest request) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.AUTHOR_NOT_EXISTED));
        authorMapper.updateAuthor(author, request);

        return authorMapper.toAuthorResponse(authorRepository.save(author));
    }
}
