package ntd.calculator.repository;

import ntd.calculator.domain.User;
import ntd.calculator.enums.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void countAllUsersTest() {
        assertThat(userRepository.count()).isEqualTo(3);
    }

    @Test
    void countActiveUsersTest() {
        List<User> activeUsers = userRepository.findByStatus(UserStatus.ACTIVE);
        assertThat(activeUsers.size()).isEqualTo(2);
    }

    @Test
    void countInactiveUsersTest() {
        List<User> inactiveUsers = userRepository.findByStatus(UserStatus.INACTIVE);
        assertThat(inactiveUsers.size()).isEqualTo(1);
    }

    @Test
    void findByUsernameAndStatusSuccessTest() {
        assertThat(userRepository.findByUsernameAndStatus(
                "oscar.santamaria@ntdsoftware.com", UserStatus.ACTIVE)
        ).isNotNull();
    }

    @Test
    void findByUsernameAndStatusFailedTest() {
        assertThat(userRepository.findByUsernameAndStatus(
                "oscar.santamaria@ntdsoftware.com", UserStatus.INACTIVE)
        ).isNull();
    }
}
