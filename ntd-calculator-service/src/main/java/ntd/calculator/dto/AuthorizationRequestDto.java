package ntd.calculator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import static ntd.calculator.constants.ApplicationConstants.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorizationRequestDto {

    @Email(message = USERNAME_EMAIL_VALIDATION_MSG)
    @NotEmpty(message = USERNAME_NOT_EMPTY_VALIDATION_MSG)
    @Size(min = 1, max = 255, message = USERNAME_SIZE_VALIDATION_MSG)
    private String username;

    @NotEmpty(message = PASSWORD_NOT_EMPTY_VALIDATION_MSG)
    @Size(min = 1, max = 255, message = PASSWORD_SIZE_VALIDATION_MSG)
    private String password;

}
