package ntd.calculator.controller;

import ntd.calculator.domain.User;
import ntd.calculator.domain.UserStatus;
import ntd.calculator.dto.AuthorizationRequestDto;
import ntd.calculator.dto.AuthorizationResponseDto;
import ntd.calculator.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;

import static ntd.calculator.util.TestUtil.mapToJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AuthorizationControllerTest {

    @InjectMocks
    AuthorizationController authorizationController;

    @Mock
    private UserService userService;

    private final AuthorizationRequestDto authorizationRequestDto =
            AuthorizationRequestDto.builder()
                    .username("oscar.santamaria@ntdsoftware.com")
                    .password("user1pwd")
                    .build();

    private final AuthorizationRequestDto invalidAuthorizationRequestDto =
            AuthorizationRequestDto.builder()
                    .username("")
                    .password("")
                    .build();

    private final User user = new User("oscar.santamaria@ntdsoftware.com", "user1pwd",
            UserStatus.ACTIVE, new BigDecimal(5000));

    private final AuthorizationResponseDto authorizationResponseDto =
            AuthorizationResponseDto.builder()
                    .username(user.getUsername())
                    .userStatus(user.getStatus())
                    .balance(user.getBalance())
                    .build();

    @Test
    void successfulAuthenticationTest() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userService.findByUsernameAndStatus(anyString(), any(UserStatus.class)))
                .thenReturn(user);

        ResponseEntity<?> responseEntity =
                authorizationController.authorizeUser(authorizationRequestDto);

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(mapToJson(responseEntity.getBody()))
                .isEqualTo(mapToJson(authorizationResponseDto));
    }

    @Test
    void unauthorizedAuthenticationTest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userService.findByUsernameAndStatus(anyString(), any(UserStatus.class)))
                .thenReturn(null);

        ResponseEntity<?> responseEntity =
                authorizationController.authorizeUser(authorizationRequestDto);

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.UNAUTHORIZED);

    }

}
