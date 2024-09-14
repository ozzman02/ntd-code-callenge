package com.ntd.service.impl;

import com.ntd.domain.User;
import com.ntd.enums.UserStatus;
import com.ntd.repository.UserRepository;
import com.ntd.service.UserService;
import lombok.RequiredArgsConstructor;
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

}
