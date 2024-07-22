package com.trinity.bookstore.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorDto {
    @NotBlank(message = "FULL_NAME_KEY")
    String fullName;

    @NotNull(message = "DOB_KEY")
    LocalDate dob;

    @NotBlank(message = "ADDRESS_KEY")
    String address;
}
