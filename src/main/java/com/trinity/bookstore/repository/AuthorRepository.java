package com.trinity.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trinity.bookstore.entity.AuthorEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findByFullNameAndDob(String fullName, LocalDate dob);
}
