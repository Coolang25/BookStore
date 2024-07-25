package com.trinity.bookstore.dto.response;

import java.time.LocalDate;

import com.trinity.bookstore.dto.request.AuthorCreationRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse {
    Long id;
    String title;
    AuthorCreationRequest author;
    LocalDate releaseDate;
    int available;
}
