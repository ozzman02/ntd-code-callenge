package ntd.calculator.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import ntd.calculator.domain.Operation;
import ntd.calculator.domain.OperationType;
import ntd.calculator.domain.User;
import ntd.calculator.domain.UserRecord;
import ntd.calculator.dto.MathematicalExpressionDto;
import ntd.calculator.dto.UserRecordDto;
import ntd.calculator.repository.UserRecordRepository;
import ntd.calculator.service.CalculatorService;
import ntd.calculator.service.OperationService;
import ntd.calculator.service.UserRecordService;
import ntd.calculator.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static ntd.calculator.mapper.UserRecordMapper.mapToUserRecordDto;
import static ntd.calculator.util.ApplicationUtil.isValidMathematicalExpression;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculatorServiceImpl implements CalculatorService {
    private final UserRecordRepository userRecordRepository;

    private final OperationService operationService;

    private final UserService userService;

    private final UserRecordService userRecordService;

    @Override
    public UserRecordDto calculate(MathematicalExpressionDto mathematicalExpressionDto) {

        log.info("Processing {} expression ", mathematicalExpressionDto.getMathematicalExpression().trim());

        UserRecord userRecord = new UserRecord();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mathematicalExpression = mathematicalExpressionDto.getMathematicalExpression().trim();

        Double result;

        User user = userService.findByUsername(authentication.getName());
        Operation operation = operationService.getValidOperationData(mathematicalExpression);

        log.info("User {} Balance is: {} - Operation Type is: {} - Operation Cost is: {}",
                user.getUsername(), user.getBalance(), operation.getOperationType(), operation.getCost());

        if (user.getBalance().signum() > 0 && user.getBalance().compareTo(operation.getCost()) > 0) {
            if (isValidMathematicalExpression(mathematicalExpression)) {
                log.info("Validating expression {} ",  mathematicalExpression);
                try {
                    Expression expression = new ExpressionBuilder(mathematicalExpression).build();
                    result = expression.evaluate();
                    log.info("Calculation result is: {} ", result);
                    user.setBalance(user.getBalance().subtract(operation.getCost()));
                    User updatedUser = userService.save(user);
                    log.info("Updated Balance is: {} - Success record will be created",
                            updatedUser.getBalance());
                    userRecord = createUserRecord(operation, user, operation.getCost(),
                            updatedUser.getBalance(), mathematicalExpression, result);
                } catch (Exception e) {
                    log.error("Mathematical expression: {} is invalid. Invalid record will be created",
                            mathematicalExpression);
                    operation = operationService.findByOperationType(OperationType.INVALID);
                    userRecord = createUserRecord(operation, user, operation.getCost(),
                            user.getBalance(), mathematicalExpression, null);
                }
            }
        } else {
            log.error("Insufficient balance - Invalid record will be created");
            operation = operationService.findByOperationType(OperationType.INVALID);
            userRecord = createUserRecord(operation, user, operation.getCost(),
                    user.getBalance(), mathematicalExpression, null);

        }
        UserRecord savedUserRecord = userRecordService.save(userRecord);
        log.info("User record successfully created");
        return mapToUserRecordDto(savedUserRecord);
    }

    private UserRecord createUserRecord(Operation operation,
                                        User user,
                                        BigDecimal amount,
                                        BigDecimal userBalance,
                                        String operationValue,
                                        Double operationResponse) {
        return new UserRecord(operation, user, amount, userBalance, operationValue, operationResponse);
    }




}
