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
    @NotBlank(message = "TITLE_KEY")
    String title;

    @NotNull
    @Valid
    AuthorCreationRequest author;

    @NotNull(message = "DATE_KEY")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    LocalDate releaseDate;

    @NotBlank(message = "QUANTITY_KEY")
    int quantity;
}
