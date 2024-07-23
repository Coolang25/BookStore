package com.trinity.bookstore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.trinity.bookstore.dto.BookDto;
import com.trinity.bookstore.entity.AuthorEntity;
import com.trinity.bookstore.entity.BookEntity;
import com.trinity.bookstore.exception.AppException;
import com.trinity.bookstore.exception.ErrorCode;
import com.trinity.bookstore.mapper.BookMapper;
import com.trinity.bookstore.repository.AuthorRepository;
import com.trinity.bookstore.repository.BookRepository;
import com.trinity.bookstore.service.BookService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    AuthorRepository authorRepository;
    BookMapper bookMapper;

    @Override
    public BookDto createBook(BookDto request) {
        BookEntity book = bookMapper.toBookEntity(request);
        Optional<AuthorEntity> author = authorRepository.findByFullNameAndDob(
                request.getAuthor().getFullName(), request.getAuthor().getDob());
        if (author.isPresent()) {
            if (bookRepository.existsByTitle(request.getTitle())) {
                throw new AppException(ErrorCode.BOOK_EXISTED);
            }
            book.setAuthor(author.get());
        }

        book = bookRepository.save(book);

        return bookMapper.toBookDto(book);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<BookEntity> books = bookRepository.findAll();

        return books.stream().map(bookMapper::toBookDto).collect(Collectors.toList());
    }
}
