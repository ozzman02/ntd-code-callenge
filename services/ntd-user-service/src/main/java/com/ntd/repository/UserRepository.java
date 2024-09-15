package com.ntd.repository;

import com.ntd.domain.User;
import com.ntd.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameAndStatus(String username, UserStatus userStatus);

    List<User> findByStatus(UserStatus userStatus);

}
