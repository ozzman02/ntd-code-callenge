package com.ntd.dto;

import com.ntd.enums.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
public class UserDto {
    private String id;
    private String username;
    private String password;
    private BigDecimal balance;
    private UserStatus status;
    private Timestamp createdDate;
}
