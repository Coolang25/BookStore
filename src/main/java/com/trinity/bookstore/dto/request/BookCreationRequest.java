package com.trinity.bookstore.dto.request;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookCreationRequest {
    @NotBlank(message = "INVALID_TITLE")
    String title;

    @NotNull
    @Valid
    AuthorCreationRequest author;

    @NotNull(message = "INVALID_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    LocalDate releaseDate;

    @NotBlank(message = "INVALID_QUANTITY")
    int quantity;
}
