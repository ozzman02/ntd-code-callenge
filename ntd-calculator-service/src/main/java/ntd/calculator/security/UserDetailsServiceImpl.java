package ntd.calculator.security;

import lombok.RequiredArgsConstructor;
import ntd.calculator.domain.User;
import ntd.calculator.domain.UserStatus;
import ntd.calculator.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static ntd.calculator.constants.ApplicationConstants.USERNAME_NOT_FOUND_ERROR_MSG;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(userService.findByUsernameAndStatus(username, UserStatus.ACTIVE))
                .map(this::mapUserToCustomUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USERNAME_NOT_FOUND_ERROR_MSG, username)));
    }

    private CustomUserDetails mapUserToCustomUserDetails(User user) {
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
