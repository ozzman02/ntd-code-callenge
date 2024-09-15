package ntd.calculator.service;

import ntd.calculator.domain.Operation;
import ntd.calculator.domain.User;
import ntd.calculator.domain.UserRecord;
import ntd.calculator.dto.MathematicalExpressionDto;
import ntd.calculator.dto.UserRecordDto;
import ntd.calculator.enums.OperationType;
import ntd.calculator.enums.UserStatus;
import ntd.calculator.service.impl.CalculatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {

    @InjectMocks
    private CalculatorServiceImpl calculatorService;

    @Mock
    private OperationService operationService;

    @Mock
    private UserService userService;

    @Mock
    private UserRecordService userRecordService;

    private MathematicalExpressionDto validMathematicalExpressionDto;

    private MathematicalExpressionDto notSupportedMathematicalExpressionDto;

    private UserRecordDto validUserRecordDto;

    private UserRecordDto invalidUserRecordDto;

    private User user;

    private User updatedUser;

    private Operation standardOperation;

    private Operation invalidOperation;

    private UserRecord userRecord;

    private UserRecord invalidUserRecord;

    private Authentication authentication;

    private SecurityContext securityContext;

    @BeforeEach
    void setup() {

        validMathematicalExpressionDto = new MathematicalExpressionDto("10*25+50000/2");
        notSupportedMathematicalExpressionDto = new MathematicalExpressionDto("10000/1000*cos(700)");

        validUserRecordDto = new UserRecordDto();
        validUserRecordDto.setId(1L);
        validUserRecordDto.setOperationId(1L);
        validUserRecordDto.setOperationType(OperationType.STANDARD);
        validUserRecordDto.setUserId(1L);
        validUserRecordDto.setUsername("oscar.santamaria@ntdsoftware.com");
        validUserRecordDto.setAmount(new BigDecimal(1));
        validUserRecordDto.setUserBalance(new BigDecimal(4999));
        validUserRecordDto.setOperationValue("10*25+50000/2");
        validUserRecordDto.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        validUserRecordDto.setOperationResponse(25250D);

        invalidUserRecordDto = new UserRecordDto();
        invalidUserRecordDto.setId(2L);
        invalidUserRecordDto.setOperationId(4L);
        invalidUserRecordDto.setOperationType(OperationType.INVALID);
        invalidUserRecordDto.setUserId(1L);
        invalidUserRecordDto.setUsername("oscar.santamaria@ntdsoftware.com");
        invalidUserRecordDto.setAmount(new BigDecimal(0));
        invalidUserRecordDto.setUserBalance(new BigDecimal(5000));
        invalidUserRecordDto.setOperationValue("10000/1000*cos(700)");
        invalidUserRecordDto.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        invalidUserRecordDto.setOperationResponse(null);

        user = new User();
        user.setId(1L);
        user.setUsername("oscar.santamaria@ntdsoftware.com");
        user.setPassword("testPassword");
        user.setStatus(UserStatus.ACTIVE);
        user.setBalance(new BigDecimal(5000));
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setUsername("oscar.santamaria@ntdsoftware.com");
        updatedUser.setPassword("testPassword");
        updatedUser.setStatus(UserStatus.ACTIVE);
        updatedUser.setBalance(new BigDecimal(4999));
        updatedUser.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        standardOperation = new Operation();
        standardOperation.setId(1L);
        standardOperation.setOperationType(OperationType.STANDARD);
        standardOperation.setCost(new BigDecimal(1));
        standardOperation.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        invalidOperation = new Operation();
        invalidOperation.setId(4L);
        invalidOperation.setOperationType(OperationType.INVALID);
        invalidOperation.setCost(new BigDecimal(0));
        invalidOperation.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        userRecord = new UserRecord();
        userRecord.setId(1L);
        userRecord.setOperation(standardOperation);
        userRecord.setUser(user);
        userRecord.setAmount(new BigDecimal(1));
        userRecord.setUserBalance(new BigDecimal(4999));
        userRecord.setOperationValue("10*25+50000/2");
        userRecord.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        userRecord.setOperationResponse(25250D);

        invalidUserRecord = new UserRecord();
        invalidUserRecord.setId(2L);
        invalidUserRecord.setOperation(invalidOperation);
        invalidUserRecord.setUser(user);
        invalidUserRecord.setAmount(new BigDecimal(0));
        invalidUserRecord.setUserBalance(new BigDecimal(5000));
        invalidUserRecord.setOperationValue("10000/1000*cos(700)");
        invalidUserRecord.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        invalidUserRecord.setOperationResponse(null);

        authentication = mock(Authentication.class);
        securityContext = mock(SecurityContext.class);

        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void calculateValidMathematicalExpression() {

        when(securityContext.getAuthentication()).thenReturn(authentication);

        //SecurityContextHolder.setContext(securityContext);

        given(userService.findByUsername(authentication.getName()))
                .willReturn(user);

        given(operationService.getValidOperationData(validMathematicalExpressionDto.
                getMathematicalExpression())).willReturn(standardOperation);

        given(userService.save(user)).willReturn(updatedUser);

        given(userRecordService.save(any())).willReturn(userRecord);

        UserRecordDto savedUserRecord =
                calculatorService.calculate(validMathematicalExpressionDto);

        assertThat(savedUserRecord).isNotNull();
        assertThat(savedUserRecord).isEqualTo(validUserRecordDto);
    }

    @Test
    void calculateMathematicalExpressionNotSupported() {

        when(securityContext.getAuthentication()).thenReturn(authentication);

        given(userService.findByUsername(authentication.getName()))
                .willReturn(user);

        given(operationService.getValidOperationData(notSupportedMathematicalExpressionDto.
                getMathematicalExpression())).willReturn(standardOperation);

        given(operationService.findByOperationType(OperationType.INVALID))
                .willReturn(invalidOperation);

        given(userRecordService.save(any())).willReturn(invalidUserRecord);

        UserRecordDto savedUserRecord =
                calculatorService.calculate(notSupportedMathematicalExpressionDto);

        assertThat(savedUserRecord).isNotNull();
        assertThat(savedUserRecord).isEqualTo(invalidUserRecordDto);
    }

    @Test
    void notEnoughUserBalanceTest() {

        when(securityContext.getAuthentication()).thenReturn(authentication);

        user.setBalance(new BigDecimal(0));

        given(userService.findByUsername(authentication.getName()))
                .willReturn(user);

        given(operationService.getValidOperationData(validMathematicalExpressionDto.
                getMathematicalExpression())).willReturn(standardOperation);

        invalidUserRecord.setUserBalance(new BigDecimal(0));
        invalidUserRecord.setOperationValue("10*25+50000/2");

        given(userRecordService.save(any())).willReturn(invalidUserRecord);

        UserRecordDto savedUserRecord =
                calculatorService.calculate(validMathematicalExpressionDto);

        invalidUserRecordDto.setUserBalance(new BigDecimal(0));
        invalidUserRecordDto.setOperationValue("10*25+50000/2");

        assertThat(savedUserRecord).isNotNull();
        assertThat(savedUserRecord).isEqualTo(invalidUserRecordDto);
    }


}
