package com.trinity.bookstore.service;

import java.time.LocalDate;
import java.util.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.trinity.bookstore.dto.AuthorDto;
import com.trinity.bookstore.entity.AuthorEntity;
import com.trinity.bookstore.entity.BookEntity;
import com.trinity.bookstore.exception.AppException;
import com.trinity.bookstore.repository.AuthorRepository;
import com.trinity.bookstore.repository.BookRepository;

@SpringBootTest
@TestPropertySource("/test.properties")
public class AuthorServiceTest {

    @Autowired
    AuthorService authorService;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    BookRepository bookRepository;

    private AuthorDto request;
    private AuthorEntity author;
    private BookEntity book1;
    private BookEntity book2;
    List<BookEntity> bookEntities;

    @BeforeEach
    private void initData() {

        request = AuthorDto.builder()
                .fullName("Trinh Trong Quat")
                .dob(LocalDate.parse("1999-11-25"))
                .address("HN")
                .build();

        author = AuthorEntity.builder()
                .id(1L)
                .fullName("Trinh Trong Quat")
                .dob(LocalDate.parse("1999-11-25"))
                .address("HN")
                .build();

        book1 = BookEntity.builder()
                .title("A")
                .releaseDate(LocalDate.parse("1999-11-25"))
                .author(author)
                .build();

        book2 = BookEntity.builder()
                .title("B")
                .releaseDate(LocalDate.parse("2000-11-25"))
                .author(author)
                .build();

        bookEntities = Arrays.asList(book1, book2);
    }

    @Test
    void createAuthor_validRequest_success() {
        // GIVEN
        Mockito.when(authorRepository.findByFullNameAndDob(Mockito.anyString(), Mockito.any()))
                .thenReturn(Optional.empty());
        Mockito.when(authorRepository.save(Mockito.any())).thenReturn(author);

        // WHEN
        AuthorDto result = authorService.createAuthor(request);

        // THEN
        Assertions.assertThat(result.getFullName()).isEqualTo("Trinh Trong Quat");
        Assertions.assertThat(result.getDob()).isEqualTo(LocalDate.parse("1999-11-25"));
    }

    @Test
    void createAuthor_authorExisted_fail() {
        // GIVEN
        Mockito.when(authorRepository.findByFullNameAndDob(Mockito.anyString(), Mockito.any()))
                .thenReturn(Optional.of(author));

        // WHEN
        var exception = org.junit.jupiter.api.Assertions.assertThrows(
                AppException.class, () -> authorService.createAuthor(request));

        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1003);
    }

    @Test
    void getAllAuthorsOwnBooks_success() {
        // GIVEN
        Mockito.when(bookRepository.findAll()).thenReturn(bookEntities);

        // WHEN
        Set<AuthorDto> result = authorService.getAllAuthorsOwnBooks();

        // THEN
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.stream().findFirst().get().getFullName()).isEqualTo("Trinh Trong Quat");
    }
}
