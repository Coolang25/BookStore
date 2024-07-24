package com.trinity.bookstore.service;

import java.util.List;

import com.trinity.bookstore.dto.request.UserCreationRequest;
import com.trinity.bookstore.dto.request.UserUpdateRequest;
import com.trinity.bookstore.dto.response.UserResponse;

public interface IUserService {
    UserResponse createUser(UserCreationRequest request);

    List<UserResponse> getUsers();

    UserResponse getUser(Long userId);

    UserResponse getMyInfo();

    UserResponse updateUser(Long userId, UserUpdateRequest request);

    void deleteUser(Long userId);
}
