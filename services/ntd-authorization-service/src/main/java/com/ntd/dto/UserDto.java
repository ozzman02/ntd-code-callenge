package com.ntd.dto;

import com.ntd.enums.Role;
import com.ntd.enums.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UserDto {
    private String id;
    private String username;
    private String password;
    private BigDecimal balance;
    private UserStatus userStatus;
    private Role role;

}
