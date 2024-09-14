package com.ntd.service;

import com.ntd.domain.User;
import com.ntd.enums.UserStatus;

public interface UserService {

    User findByUsername(String username);

    User findByUsernameAndStatus(String username, UserStatus userStatus);

}
