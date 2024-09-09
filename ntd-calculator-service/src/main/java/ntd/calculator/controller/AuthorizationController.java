package ntd.calculator.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ntd.calculator.domain.UserStatus;
import ntd.calculator.dto.AuthorizationRequestDto;
import ntd.calculator.dto.AuthorizationResponseDto;
import ntd.calculator.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static ntd.calculator.constants.ApplicationConstants.AUTH_URL;

@RestController
@RequestMapping(AUTH_URL)
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> authorizeUser(@Valid @RequestBody AuthorizationRequestDto authorizationRequestDto) {
        return Optional.ofNullable(userService.findByUsernameAndStatus(
                authorizationRequestDto.getUsername(), UserStatus.ACTIVE)).map(user -> {
            AuthorizationResponseDto authorizationResponse =
                    AuthorizationResponseDto.builder().username(user.getUsername())
                            .userStatus(user.getStatus()).balance(user.getBalance())
                            .build();
            return new ResponseEntity<>(authorizationResponse, HttpStatus.OK);
        }).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}
