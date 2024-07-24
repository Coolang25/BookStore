package com.trinity.bookstore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.trinity.bookstore.dto.request.BookUpdateRequest;
import com.trinity.bookstore.dto.response.BookResponse;
import org.springframework.stereotype.Service;

import com.trinity.bookstore.dto.request.BookCreationRequest;
import com.trinity.bookstore.entity.Author;
import com.trinity.bookstore.entity.Book;
import com.trinity.bookstore.exception.AppException;
import com.trinity.bookstore.exception.ErrorCode;
import com.trinity.bookstore.mapper.BookMapper;
import com.trinity.bookstore.repository.AuthorRepository;
import com.trinity.bookstore.repository.BookRepository;
import com.trinity.bookstore.service.IBookService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService implements IBookService {

    BookRepository bookRepository;
    AuthorRepository authorRepository;
    BookMapper bookMapper;

    @Override
    public BookResponse createBook(BookCreationRequest request) {
        Book book = bookMapper.toBook(request);
        book.setAvailable(request.getQuantity());
        book.setDeleted(false);
        Optional<Author> author = authorRepository.findByFullNameAndDob(
                request.getAuthor().getFullName(), request.getAuthor().getDob());
        if (author.isPresent()) {
            if (bookRepository.existsByTitle(request.getTitle())) {
                throw new AppException(ErrorCode.BOOK_EXISTED);
            }
            book.setAuthor(author.get());
        }

        book = bookRepository.save(book);

        return bookMapper.toBookResponse(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAllByDeleted(false);

        return books.stream().map(bookMapper::toBookResponse).collect(Collectors.toList());
    }

    @Override
    public BookResponse getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_EXISTED));
        return bookMapper.toBookResponse(book);
    }

    @Override
    public BookResponse updateBook(Long id, BookUpdateRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_EXISTED));

        bookMapper.updateBook(book, request);

        return bookMapper.toBookResponse(bookRepository.save(book));
    }

    @Override
    public String deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_EXISTED));
        book.setDeleted(true);

        return "Delete book successfully";
    }
}
