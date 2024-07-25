package com.trinity.bookstore.api;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.trinity.bookstore.dto.request.UserCreationRequest;
import com.trinity.bookstore.dto.request.UserUpdateRequest;
import com.trinity.bookstore.dto.response.ApiResponse;
import com.trinity.bookstore.dto.response.UserResponse;
import com.trinity.bookstore.service.IUserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserRestController {
    IUserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@Valid @RequestBody UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable Long userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}
