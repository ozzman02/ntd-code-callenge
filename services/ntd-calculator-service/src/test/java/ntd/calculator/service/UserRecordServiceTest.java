package ntd.calculator.service;

import ntd.calculator.domain.Operation;
import ntd.calculator.domain.User;
import ntd.calculator.domain.UserRecord;
import ntd.calculator.enums.OperationType;
import ntd.calculator.enums.UserStatus;
import ntd.calculator.repository.UserRecordRepository;
import ntd.calculator.service.impl.UserRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserRecordServiceTest {

    @InjectMocks
    private UserRecordServiceImpl userRecordService;

    @Mock
    private UserRecordRepository userRecordRepository;

    @Test
    void saveUserRecordTest() {
        Operation standardOperation = new Operation();
        standardOperation.setId(1L);
        standardOperation.setOperationType(OperationType.STANDARD);
        standardOperation.setCost(new BigDecimal(1));
        standardOperation.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        User user = new User();
        user.setId(1L);
        user.setUsername("oscar.santamaria@ntdsoftware.com");
        user.setPassword("testPassword");
        user.setStatus(UserStatus.ACTIVE);
        user.setBalance(new BigDecimal(5000));

        BigDecimal amount = new BigDecimal(1);
        BigDecimal userBalance = new BigDecimal(4999);
        String operationValue = "50+50";
        Double operationResponse = 100D;

        UserRecord userRecord = new UserRecord();
        userRecord.setId(1L);
        userRecord.setOperation(standardOperation);
        userRecord.setUser(user);
        userRecord.setAmount(amount);
        userRecord.setUserBalance(userBalance);
        userRecord.setOperationValue(operationValue);
        userRecord.setOperationResponse(operationResponse);
        userRecord.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        given(userRecordRepository.save(userRecord)).willReturn(userRecord);

        UserRecord savedUserRecord = userRecordService.save(userRecord);

        assertThat(savedUserRecord).isNotNull();

    }
}
