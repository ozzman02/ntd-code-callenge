package ntd.calculator.dto;

import lombok.*;
import ntd.calculator.domain.UserStatus;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorizationResponseDto {

    private String username;

    private UserStatus userStatus;

    private BigDecimal balance;

}
