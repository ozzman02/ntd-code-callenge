package ntd.calculator.repository;

import ntd.calculator.domain.User;
import ntd.calculator.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameAndStatus(String username, UserStatus userStatus);

    List<User> findByStatus(UserStatus userStatus);

    @Modifying
    @Query("UPDATE User u set u.balance = ?1 WHERE u.username = ?2")
    void updateBalance(BigDecimal balance, String username);

}
