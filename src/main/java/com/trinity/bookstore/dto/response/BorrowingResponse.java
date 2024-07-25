package com.trinity.bookstore.dto.response;

import com.trinity.bookstore.entity.Book;
import com.trinity.bookstore.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BorrowingResponse {
    Long id;
    UserResponse user;
    BookResponse book;

    LocalDateTime borrowDate;
    LocalDateTime returnDate;

    boolean returned;
}
