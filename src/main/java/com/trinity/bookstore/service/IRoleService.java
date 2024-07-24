package com.trinity.bookstore.service;

import com.trinity.bookstore.dto.request.RoleRequest;
import com.trinity.bookstore.dto.response.RoleResponse;

import java.util.List;
public interface IRoleService {
    RoleResponse create(RoleRequest request);

    List<RoleResponse> getAll();

    void delete(Long id);
}
