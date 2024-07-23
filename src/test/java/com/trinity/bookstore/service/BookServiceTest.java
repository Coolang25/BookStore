package com.trinity.bookstore.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.trinity.bookstore.dto.AuthorDto;
import com.trinity.bookstore.dto.BookDto;
import com.trinity.bookstore.entity.AuthorEntity;
import com.trinity.bookstore.entity.BookEntity;
import com.trinity.bookstore.exception.AppException;
import com.trinity.bookstore.repository.AuthorRepository;
import com.trinity.bookstore.repository.BookRepository;

@SpringBootTest
@TestPropertySource("/test.properties")
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    BookRepository bookRepository;

    private AuthorDto authorDto;
    private BookDto request;
    private AuthorEntity authorEntity;
    private BookEntity book1;
    private BookEntity book2;
    List<BookEntity> bookEntities;

    @BeforeEach
    private void initData() {
        authorDto = AuthorDto.builder()
                .fullName("Trinh Trong Quat")
                .dob(LocalDate.parse("1999-11-25"))
                .address("HN")
                .build();

        authorEntity = AuthorEntity.builder()
                .id(1L)
                .fullName("Trinh Trong Quat")
                .dob(LocalDate.parse("1999-11-25"))
                .address("HN")
                .build();

        request = BookDto.builder()
                .title("Chieu toi")
                .releaseDate(LocalDate.parse("1999-11-25"))
                .author(authorDto)
                .build();

        book1 = BookEntity.builder()
                .id(1L)
                .title("Chieu toi")
                .releaseDate(LocalDate.parse("1999-11-25"))
                .author(authorEntity)
                .build();

        book2 = BookEntity.builder()
                .id(2L)
                .title("B")
                .releaseDate(LocalDate.parse("2000-11-25"))
                .author(authorEntity)
                .build();

        bookEntities = Arrays.asList(book1, book2);
    }

    @Test
    void createBook_validRequest_success() {
        // GIVEN
        Mockito.when(authorRepository.findByFullNameAndDob(Mockito.anyString(), Mockito.any()))
                .thenReturn(Optional.empty());
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book1);

        // WHEN
        BookDto result = bookService.createBook(request);

        // THEN
        Assertions.assertThat(result.getTitle()).isEqualTo("Chieu toi");
        Assertions.assertThat(result.getAuthor().getFullName()).isEqualTo("Trinh Trong Quat");
    }

    @Test
    void createBook_bookExisted_fail() {
        // GIVEN
        Mockito.when(authorRepository.findByFullNameAndDob(Mockito.anyString(), Mockito.any()))
                .thenReturn(Optional.of(authorEntity));
        Mockito.when(bookRepository.existsByTitle(Mockito.anyString())).thenReturn(true);

        // WHEN
        var exception = org.junit.jupiter.api.Assertions.assertThrows(
                AppException.class, () -> bookService.createBook(request));

        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1004);
    }

    @Test
    void getAllBooks_success() {
        // GIVEN
        Mockito.when(bookRepository.findAll()).thenReturn(bookEntities);

        // WHEN
        List<BookDto> result = bookService.getAllBooks();

        // THEN
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.stream().findFirst().get().getAuthor().getFullName())
                .isEqualTo("Trinh Trong Quat");
    }
}
