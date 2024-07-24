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

import com.trinity.bookstore.dto.request.AuthorCreationRequest;
import com.trinity.bookstore.dto.request.BookCreationRequest;
import com.trinity.bookstore.dto.response.BookResponse;
import com.trinity.bookstore.entity.Author;
import com.trinity.bookstore.entity.Book;
import com.trinity.bookstore.exception.AppException;
import com.trinity.bookstore.repository.AuthorRepository;
import com.trinity.bookstore.repository.BookRepository;

@SpringBootTest
@TestPropertySource("/test.properties")
public class IBookServiceTest {

    @Autowired
    IBookService iBookService;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    BookRepository bookRepository;

    private AuthorCreationRequest authorDto;
    private BookCreationRequest request;
    private Author author;
    private Book book1;
    private Book book2;
    List<Book> bookEntities;

    @BeforeEach
    private void initData() {
        authorDto = AuthorCreationRequest.builder()
                .fullName("Trinh Trong Quat")
                .dob(LocalDate.parse("1999-11-25"))
                .address("HN")
                .build();

        author = Author.builder()
                .id(1L)
                .fullName("Trinh Trong Quat")
                .dob(LocalDate.parse("1999-11-25"))
                .address("HN")
                .build();

        request = BookCreationRequest.builder()
                .title("Chieu toi")
                .releaseDate(LocalDate.parse("1999-11-25"))
                .author(authorDto)
                .build();

        book1 = Book.builder()
                .id(1L)
                .title("Chieu toi")
                .releaseDate(LocalDate.parse("1999-11-25"))
                .author(author)
                .build();

        book2 = Book.builder()
                .id(2L)
                .title("B")
                .releaseDate(LocalDate.parse("2000-11-25"))
                .author(author)
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
        BookResponse result = iBookService.createBook(request);

        // THEN
        Assertions.assertThat(result.getTitle()).isEqualTo("Chieu toi");
        Assertions.assertThat(result.getAuthor().getFullName()).isEqualTo("Trinh Trong Quat");
    }

    @Test
    void createBook_bookExisted_fail() {
        // GIVEN
        Mockito.when(authorRepository.findByFullNameAndDob(Mockito.anyString(), Mockito.any()))
                .thenReturn(Optional.of(author));
        Mockito.when(bookRepository.existsByTitle(Mockito.anyString())).thenReturn(true);

        // WHEN
        var exception = org.junit.jupiter.api.Assertions.assertThrows(
                AppException.class, () -> iBookService.createBook(request));

        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1004);
    }

    @Test
    void getAllBooks_success() {
        // GIVEN
        Mockito.when(bookRepository.findAll()).thenReturn(bookEntities);

        // WHEN
        List<BookResponse> result = iBookService.getAllBooks();

        // THEN
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.stream().findFirst().get().getAuthor().getFullName())
                .isEqualTo("Trinh Trong Quat");
    }
}
