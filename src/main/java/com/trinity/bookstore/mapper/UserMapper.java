package com.trinity.bookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.trinity.bookstore.dto.request.UserCreationRequest;
import com.trinity.bookstore.dto.request.UserUpdateRequest;
import com.trinity.bookstore.dto.response.UserResponse;
import com.trinity.bookstore.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
