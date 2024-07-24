package com.trinity.bookstore.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "borrowing")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    User user;

    @ManyToOne(cascade = CascadeType.ALL)
    Book book;

    LocalDateTime borrowDate;
    LocalDateTime returnDate;
}
