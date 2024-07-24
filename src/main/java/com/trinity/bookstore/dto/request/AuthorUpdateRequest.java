package com.trinity.bookstore.dto.request;

import java.time.LocalDate;

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
public class AuthorUpdateRequest {
    @NotBlank(message = "INVALID_FULL_NAME")
    String fullName;

    @NotNull(message = "INVALID_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    LocalDate dob;

    @NotBlank(message = "INVALID_ADDRESS")
    String address;
}
