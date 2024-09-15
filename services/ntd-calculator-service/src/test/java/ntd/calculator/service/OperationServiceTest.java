package ntd.calculator.service;

import ntd.calculator.domain.Operation;
import ntd.calculator.enums.OperationType;
import ntd.calculator.repository.OperationRepository;
import ntd.calculator.service.impl.OperationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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
public class OperationServiceTest {

    @InjectMocks
    private OperationServiceImpl operationService;

    @Mock
    private OperationRepository operationRepository;

    private final Operation standardOperation = new Operation();

    private final Operation complexOperation = new Operation();

    private final Operation specialOperation = new Operation();

    private final Operation invalidOperation = new Operation();

    @BeforeEach
    void setup() {
        standardOperation.setId(1L);
        standardOperation.setOperationType(OperationType.STANDARD);
        standardOperation.setCost(new BigDecimal(1));
        standardOperation.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        complexOperation.setId(2L);
        complexOperation.setOperationType(OperationType.COMPLEX);
        complexOperation.setCost(new BigDecimal(5));
        complexOperation.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        specialOperation.setId(3L);
        specialOperation.setOperationType(OperationType.SPECIAL);
        specialOperation.setCost(new BigDecimal(10));
        specialOperation.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        invalidOperation.setId(4L);
        invalidOperation.setOperationType(OperationType.INVALID);
        invalidOperation.setCost(new BigDecimal(0));
        invalidOperation.setCreatedDate(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    void findByOperationTypeTest() {

        given(operationRepository.findByOperationType(OperationType.STANDARD))
                .willReturn(standardOperation);
        given(operationRepository.findByOperationType(OperationType.COMPLEX))
                .willReturn(complexOperation);
        given(operationRepository.findByOperationType(OperationType.SPECIAL))
                .willReturn(specialOperation);
        given(operationRepository.findByOperationType(OperationType.INVALID))
                .willReturn(invalidOperation);

        Operation standardOperationObj =
                operationService.findByOperationType(OperationType.STANDARD);
        Operation complexOperationObj =
                operationService.findByOperationType(OperationType.COMPLEX);
        Operation specialOperationObj =
                operationService.findByOperationType(OperationType.SPECIAL);
        Operation invalidOperationObj =
                operationService.findByOperationType(OperationType.INVALID);

        assertThat(standardOperationObj).isNotNull();
        assertThat(complexOperationObj).isNotNull();
        assertThat(specialOperationObj).isNotNull();
        assertThat(invalidOperationObj).isNotNull();
    }

    @Test
    void getValidOperationDataTest() {

        String standardMathExp = "10+10+15";

        String complexMathExpr = "10+10+10+40*3000/2+5000";

        String specialMathExpr = "325-500+1000+600+700+8000*100+500+1+600+700+8000*100+500+1000+600+700+15000*100+500+1+600+700+8000-150";

        given(operationRepository.findByOperationType(OperationType.STANDARD))
                .willReturn(standardOperation);
        given(operationRepository.findByOperationType(OperationType.COMPLEX))
                .willReturn(complexOperation);
        given(operationRepository.findByOperationType(OperationType.SPECIAL))
                .willReturn(specialOperation);

        Operation standardOperationObj =
                operationService.getValidOperationData(standardMathExp);
        Operation complexOperationObj =
                operationService.getValidOperationData(complexMathExpr);
        Operation specialOperationObj =
                operationService.getValidOperationData(specialMathExpr);

        assertThat(standardOperationObj.getOperationType()).isEqualTo(OperationType.STANDARD);
        assertThat(complexOperationObj.getOperationType()).isEqualTo(OperationType.COMPLEX);
        assertThat(specialOperationObj.getOperationType()).isEqualTo(OperationType.SPECIAL);
    }
}
