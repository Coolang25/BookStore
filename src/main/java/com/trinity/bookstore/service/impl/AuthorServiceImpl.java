package com.trinity.bookstore.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.trinity.bookstore.dto.AuthorDto;
import com.trinity.bookstore.entity.AuthorEntity;
import com.trinity.bookstore.entity.BookEntity;
import com.trinity.bookstore.exception.AppException;
import com.trinity.bookstore.exception.ErrorCode;
import com.trinity.bookstore.mapper.AuthorMapper;
import com.trinity.bookstore.repository.AuthorRepository;
import com.trinity.bookstore.repository.BookRepository;
import com.trinity.bookstore.service.AuthorService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;
    BookRepository bookRepository;
    AuthorMapper authorMapper;

    @Override
    public AuthorDto createAuthor(AuthorDto request) {
        Optional<AuthorEntity> author = authorRepository.findByFullNameAndDob(request.getFullName(), request.getDob());
        if (author.isPresent()) {
            throw new AppException(ErrorCode.AUTHOR_EXISTED);
        }
        AuthorEntity author1 = authorRepository.save(authorMapper.toAuthorEntity(request));

        return authorMapper.toAuthorDto(author1);
    }

    //    @Override
    //    public List<AuthorDto> getAllAuthors() {
    //        List<AuthorEntity> authors = authorRepository.findAll();
    //
    //        return authors.stream().map(authorMapper::toAuthorDto).collect(Collectors.toList());
    //    }

    @Override
    public Set<AuthorDto> getAllAuthorsOwnBooks() {
        Set<AuthorEntity> authors =
                bookRepository.findAll().stream().map(BookEntity::getAuthor).collect(Collectors.toSet());
        return authors.stream().map(authorMapper::toAuthorDto).collect(Collectors.toSet());
    }
}
