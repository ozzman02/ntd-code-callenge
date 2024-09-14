package com.ntd.service;

import com.ntd.dto.UserDto;
import com.ntd.enums.UserStatus;
import com.ntd.feign.UserServiceFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.ntd.constants.ApplicationConstants.USERNAME_NOT_FOUND_ERROR_MSG;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserServiceFeignClient userServiceFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(userServiceFeignClient
                        .getUserByUsernameAndStatus(username, UserStatus.ACTIVE))
                .map(response -> mapUserToCustomUserDetails(Objects.requireNonNull(response.getBody())))
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USERNAME_NOT_FOUND_ERROR_MSG, username)));
    }

    private CustomUserDetails mapUserToCustomUserDetails(UserDto user) {
        return CustomUserDetails.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .status(user.getStatus())
                .balance(user.getBalance())
                .authorities(null)
                .build();
    }

}
