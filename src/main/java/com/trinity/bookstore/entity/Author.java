package com.trinity.bookstore.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "authors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String fullName;
    LocalDate dob;
    String address;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    LocalDateTime modifiedDate;

    @CreatedBy
    String created_by;

    @LastModifiedBy
    String modified_by;
}
