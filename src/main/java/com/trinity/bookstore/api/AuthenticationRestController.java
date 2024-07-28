package com.trinity.bookstore.api;

import java.text.ParseException;

import com.trinity.bookstore.exception.AppException;
import com.trinity.bookstore.exception.ErrorCode;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.nimbusds.jose.JOSEException;
import com.trinity.bookstore.dto.request.AuthenticationRequest;
import com.trinity.bookstore.dto.request.IntrospectRequest;
import com.trinity.bookstore.dto.request.LogoutRequest;
import com.trinity.bookstore.dto.request.RefreshRequest;
import com.trinity.bookstore.dto.response.ApiResponse;
import com.trinity.bookstore.dto.response.AuthenticationResponse;
import com.trinity.bookstore.dto.response.IntrospectResponse;
import com.trinity.bookstore.service.IAuthenticationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationRestController {
    IAuthenticationService authenticationService;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;
    @PostMapping("/login")
    ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        String refreshToken = authenticationService.createRefreshToken(request.getEmail());
        authenticationService.updateUserRefreshToken(refreshToken, request.getEmail());

        ResponseCookie resCookie = ResponseCookie
                .from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(REFRESHABLE_DURATION)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, resCookie.toString())
                .body(ApiResponse.<AuthenticationResponse>builder().result(result).build());
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@CookieValue(name = "refresh_token", defaultValue = "abc") String refreshToken) throws ParseException, JOSEException {
        authenticationService.logout(refreshToken);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refresh(@CookieValue(name = "refresh_token", defaultValue = "abc") String refreshToken)
            throws ParseException, JOSEException {
        if (refreshToken.equals("abc")) {
            throw new AppException(ErrorCode.TOKEN_NOT_AVAILABLE);
        }

        var result = authenticationService.refreshToken(refreshToken);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }
}
