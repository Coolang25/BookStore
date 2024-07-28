package com.trinity.bookstore.service.impl;

import com.trinity.bookstore.dto.request.BorrowingRequest;
import com.trinity.bookstore.dto.response.BookResponse;
import com.trinity.bookstore.dto.response.BorrowingResponse;
import com.trinity.bookstore.entity.Book;
import com.trinity.bookstore.entity.Borrowing;
import com.trinity.bookstore.entity.User;
import com.trinity.bookstore.exception.AppException;
import com.trinity.bookstore.exception.ErrorCode;
import com.trinity.bookstore.mapper.BookMapper;
import com.trinity.bookstore.mapper.BorrowingMapper;
import com.trinity.bookstore.repository.BookRepository;
import com.trinity.bookstore.repository.BorrowingRepository;
import com.trinity.bookstore.repository.UserRepository;
import com.trinity.bookstore.service.IBorrowingService;
import com.trinity.bookstore.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BorrowingService implements IBorrowingService {
    UserRepository userRepository;
    BookRepository bookRepository;
    BorrowingRepository borrowingRepository;
    IUserService userService;
    BorrowingMapper borrowingMapper;
    BookMapper bookMapper;

    @Override
    @Transactional
    public BorrowingResponse borrowBook(BorrowingRequest request) {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_EXISTED));

        Optional<Borrowing> borrow = borrowingRepository.findByUserAndBookAndReturned(user, book, false);
        if (borrow.isPresent()) {
            throw new AppException(ErrorCode.USER_BORROWED_BOOK);
        }

        if(book.getAvailable() == 0) {
            throw new AppException(ErrorCode.BOOK_NOT_AVAILABLE);
        }

        book.setAvailable(book.getAvailable() - 1);

        Borrowing borrowing = Borrowing.builder()
                .book(book)
                .user(user)
                .borrowDate(LocalDateTime.now())
                .returnDate(null)
                .returned(false)
                .build();

        return borrowingMapper.toBorrowingResponse(borrowingRepository.save(borrowing));
    }

    @Override
    @Transactional
    public BorrowingResponse returnBook(BorrowingRequest request) {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_EXISTED));

        Borrowing borrowing = borrowingRepository.findByUserAndBook(user, book)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_BORROW_BOOK));

        if (borrowing.isReturned()) throw new AppException(ErrorCode.USER_RETURNED_BOOK);

        book.setAvailable(book.getAvailable() + 1);
        borrowing.setReturnDate(LocalDateTime.now());
        borrowing.setReturned(true);
        borrowing.setBook(book);

        return borrowingMapper.toBorrowingResponse(borrowingRepository.save(borrowing));
    }

    @Override
    public List<BookResponse> unReturnedBooks() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        List<Borrowing> borrowing = borrowingRepository.findAllByUserAndReturned(user, false);
        List<Book> books = borrowing.stream().map(Borrowing::getBook).toList();

        return books.stream().map(bookMapper::toBookResponse).collect(Collectors.toList());
    }
}
