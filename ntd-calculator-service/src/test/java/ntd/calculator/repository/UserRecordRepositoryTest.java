package ntd.calculator.repository;

import ntd.calculator.domain.Operation;
import ntd.calculator.domain.OperationType;
import ntd.calculator.domain.User;
import ntd.calculator.domain.UserRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRecordRepositoryTest {

    @Autowired
    private UserRecordRepository userRecordRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUserRecordTest() {
        Operation operation = operationRepository.findByOperationType(OperationType.STANDARD);
        assertThat(operation).isNotNull();
        BigDecimal amount = operation.getCost();

        User user = userRepository.findByUsername("oscar.santamaria@ntdsoftware.com");
        assertThat(user).isNotNull();
        BigDecimal updatedBalance = user.getBalance().subtract(amount);

        String operationValue = "100*100+1000";

        Double operationResponse = 2000D;

        UserRecord savedRecord = userRecordRepository.save(new UserRecord(operation, user, amount,
                updatedBalance, operationValue, operationResponse));

        assertThat(savedRecord).isNotNull();
    }

}
