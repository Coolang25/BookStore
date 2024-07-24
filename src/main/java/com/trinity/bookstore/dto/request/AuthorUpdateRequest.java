package com.trinity.bookstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorUpdateRequest {
    @NotBlank(message = "FULL_NAME_KEY")
    String fullName;

    @NotNull(message = "DATE_KEY")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    LocalDate dob;

    @NotBlank(message = "ADDRESS_KEY")
    String address;
}
