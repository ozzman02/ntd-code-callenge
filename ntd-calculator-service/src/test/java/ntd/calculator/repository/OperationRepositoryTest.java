package ntd.calculator.repository;

import ntd.calculator.domain.OperationType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OperationRepositoryTest {

    @Autowired
    OperationRepository operationRepository;

    @Test
    void countOperationsTest() {
        assertThat(operationRepository.count()).isEqualTo(4);
    }

    @Test
    void findByOperationTypeTest() {
        assertThat(operationRepository.findByOperationType(OperationType.STANDARD)).isNotNull();
        assertThat(operationRepository.findByOperationType(OperationType.SPECIAL)).isNotNull();
        assertThat(operationRepository.findByOperationType(OperationType.COMPLEX)).isNotNull();
        assertThat(operationRepository.findByOperationType(OperationType.INVALID)).isNotNull();
    }
}
