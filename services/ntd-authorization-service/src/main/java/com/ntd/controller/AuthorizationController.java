package com.ntd.controller;

import com.ntd.dto.TokenDto;
import com.ntd.request.LoginRequest;
import com.ntd.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ntd.constants.ApplicationConstants.AUTH_URL;
import static com.ntd.constants.ApplicationConstants.LOGIN;

@RestController
@RequestMapping(AUTH_URL)
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping(LOGIN)
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authorizationService.login(loginRequest), HttpStatus.OK);
    }

}
