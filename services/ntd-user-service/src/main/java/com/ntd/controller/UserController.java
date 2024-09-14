package com.ntd.controller;

import com.ntd.domain.User;
import com.ntd.enums.UserStatus;
import com.ntd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ntd.constants.ApplicationConstants.*;

@RestController
@RequestMapping(USER_SERVICE_URL)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(GET_BY_USERNAME)
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping(GET_BY_USERNAME_AND_STATUS)
    public ResponseEntity<User> findUserByUsernameAndStatus(@PathVariable String username,
                                                            @PathVariable UserStatus status) {
        return new ResponseEntity<>(userService.findByUsernameAndStatus(username, status), HttpStatus.OK);
    }

}
