package com.trinity.bookstore.service.impl;

import com.trinity.bookstore.dto.request.RoleRequest;
import com.trinity.bookstore.dto.response.RoleResponse;
import com.trinity.bookstore.mapper.RoleMapper;
import com.trinity.bookstore.repository.RoleRepository;
import com.trinity.bookstore.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    @Override
    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);

        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getAll() {
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
