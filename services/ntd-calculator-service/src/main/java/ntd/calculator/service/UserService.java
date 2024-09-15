package ntd.calculator.service;

import ntd.calculator.domain.User;
import ntd.calculator.enums.UserStatus;

public interface UserService {

    User findByUsername(String username);

    User findByUsernameAndStatus(String username, UserStatus userStatus);

    User save(User user);

}
