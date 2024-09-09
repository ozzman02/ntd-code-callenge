package ntd.calculator.service.impl;

import lombok.RequiredArgsConstructor;
import ntd.calculator.domain.User;
import ntd.calculator.domain.UserStatus;
import ntd.calculator.repository.UserRepository;
import ntd.calculator.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsernameAndStatus(String username, UserStatus userStatus) {
        return userRepository.findByUsernameAndStatus(username, userStatus);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByStatus(UserStatus userStatus) {
        return userRepository.findByStatus(userStatus);
    }

    @Override
    @Transactional
    public void updateBalance(BigDecimal balance, String username) {
         userRepository.updateBalance(balance, username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


}
