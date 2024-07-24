package com.trinity.bookstore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @Email(regexp = ".+@.+\\..+", message = "INVALID_EMAIL")
    String email;

    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;

    @NotBlank(message = "INVALID_FULL_NAME")
    String fullName;
}
