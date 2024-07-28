package com.trinity.bookstore.repository;

import com.trinity.bookstore.entity.Book;
import com.trinity.bookstore.entity.Borrowing;
import com.trinity.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    Optional<Borrowing> findByUserAndBook(User user, Book book);
    List<Borrowing> findAllByUserAndReturned(User user, boolean returned);

    Optional<Borrowing> findByUserAndBookAndReturned(User user, Book book, boolean returned);
}
