package com.trinity.bookstore.mapper;

import org.mapstruct.Mapper;

import com.trinity.bookstore.dto.request.RoleRequest;
import com.trinity.bookstore.dto.response.RoleResponse;
import com.trinity.bookstore.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
