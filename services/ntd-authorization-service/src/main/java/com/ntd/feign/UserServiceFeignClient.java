package com.ntd.feign;

import com.ntd.dto.RegisterUserDto;
import com.ntd.dto.UserDto;
import com.ntd.request.RegisterUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.ntd.constants.ApplicationConstants.*;

@FeignClient(name = USER_SERVICE , path = USER_SERVICE_URL)
public interface UserServiceFeignClient {

    @PostMapping(SAVE_USER_URL)
    ResponseEntity<RegisterUserDto> save(@RequestBody RegisterUserRequest registerUserRequest);

    @GetMapping(GET_USER_BY_USERNAME_URL)
    ResponseEntity<UserDto> getUserByUsername(@PathVariable String username);
}
