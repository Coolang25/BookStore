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
    IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void logout(LogoutRequest request) throws ParseException, JOSEException;

    SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
