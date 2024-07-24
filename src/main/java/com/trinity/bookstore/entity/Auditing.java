package com.trinity.bookstore.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "audits")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    User user;

    String action; // ENUM('CREATE', 'UPDATE', 'DELETE'),
    String table_name;
    String data;
    LocalDateTime created_at;
}
