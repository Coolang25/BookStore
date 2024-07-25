package com.trinity.bookstore.entity;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "email", unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String email;

    String password;
    String fullName;

    @ManyToOne(cascade = CascadeType.ALL)
    Role role;

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
