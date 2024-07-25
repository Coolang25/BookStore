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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    @ManyToOne(cascade = CascadeType.ALL)
    Author author;

    LocalDate releaseDate;

    int quantity;
    int available;
    boolean deleted;

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
