package com.ntd.service;

import com.ntd.dto.TokenDto;
import com.ntd.exception.InvalidCredentialsException;
import com.ntd.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static com.ntd.constants.ApplicationConstants.INVALID_CREDENTIALS_ERROR;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public TokenDto login(LoginRequest loginRequest) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return TokenDto.builder()
                    .token(jwtService.generateToken(loginRequest.getUsername()))
                    .build();
        } else {
            throw new InvalidCredentialsException(INVALID_CREDENTIALS_ERROR);
        }
    }

}
