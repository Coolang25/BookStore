package com.trinity.bookstore.repository;

import com.trinity.bookstore.entity.Book;
import com.trinity.bookstore.entity.Borrowing;
import com.trinity.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    Optional<Borrowing> findByUserAndBook(User user, Book book);
}
