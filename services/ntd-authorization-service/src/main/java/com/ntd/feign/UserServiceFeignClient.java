package com.ntd.feign;

import com.ntd.dto.UserDto;
import com.ntd.enums.UserStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.ntd.constants.ApplicationConstants.*;

@FeignClient(name = USER_SERVICE , path = USER_SERVICE_URL)
public interface UserServiceFeignClient {

    @GetMapping(GET_USER_BY_USERNAME)
    ResponseEntity<UserDto> getUserByUsername(@PathVariable String username);

    @GetMapping(GET_BY_USERNAME_AND_STATUS)
    ResponseEntity<UserDto> getUserByUsernameAndStatus(@PathVariable String username,
                                                       @PathVariable UserStatus status);

}
