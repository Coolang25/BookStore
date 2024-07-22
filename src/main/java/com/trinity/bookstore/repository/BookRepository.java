package com.trinity.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trinity.bookstore.entity.BookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    boolean existsByTitle(String title);
}
