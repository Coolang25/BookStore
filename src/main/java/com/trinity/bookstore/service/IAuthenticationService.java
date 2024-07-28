package com.trinity.bookstore.service;

import java.text.ParseException;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.SignedJWT;
import com.trinity.bookstore.dto.request.AuthenticationRequest;
import com.trinity.bookstore.dto.request.IntrospectRequest;
import com.trinity.bookstore.dto.request.LogoutRequest;
import com.trinity.bookstore.dto.request.RefreshRequest;
import com.trinity.bookstore.dto.response.AuthenticationResponse;
import com.trinity.bookstore.dto.response.IntrospectResponse;

public interface IAuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    public String createRefreshToken(String email);

    void logout(String token) throws ParseException, JOSEException;

    AuthenticationResponse refreshToken(String token) throws ParseException, JOSEException;

    void updateUserRefreshToken(String token, String email);

    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
