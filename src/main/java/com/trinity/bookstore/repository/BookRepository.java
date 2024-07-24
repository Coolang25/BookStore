package com.trinity.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trinity.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);

    List<Book> findAllByDeleted(boolean isDeleted);
}
