package ntd.calculator.service.impl;

import lombok.RequiredArgsConstructor;
import ntd.calculator.domain.User;
import ntd.calculator.enums.UserStatus;
import ntd.calculator.repository.UserRepository;
import ntd.calculator.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) { return userRepository.findByUsername(username); }

    @Override
    @Transactional(readOnly = true)
    public User findByUsernameAndStatus(String username, UserStatus userStatus) {
        return userRepository.findByUsernameAndStatus(username, userStatus);
    }

    @Override
    public User save(User user) { return userRepository.save(user); }

}
