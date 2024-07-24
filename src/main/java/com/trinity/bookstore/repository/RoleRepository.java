package com.trinity.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trinity.bookstore.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
