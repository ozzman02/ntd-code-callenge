package ntd.calculator.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import ntd.calculator.domain.Operation;
import ntd.calculator.domain.User;
import ntd.calculator.domain.UserRecord;
import ntd.calculator.dto.MathematicalExpressionDto;
import ntd.calculator.dto.UserRecordDto;
import ntd.calculator.enums.OperationType;
import ntd.calculator.service.CalculatorService;
import ntd.calculator.service.OperationService;
import ntd.calculator.service.UserRecordService;
import ntd.calculator.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static ntd.calculator.constants.ApplicationConstants.*;
import static ntd.calculator.mapper.UserRecordMapper.mapToUserRecordDto;
import static ntd.calculator.util.ApplicationUtil.isValidMathematicalExpression;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculatorServiceImpl implements CalculatorService {

    private final OperationService operationService;

    private final UserService userService;

    private final UserRecordService userRecordService;

    @Override
    public UserRecordDto calculate(MathematicalExpressionDto mathematicalExpressionDto) {

        log.info("Processing {} expression ", mathematicalExpressionDto.getMathematicalExpression().trim());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mathematicalExpression = mathematicalExpressionDto.getMathematicalExpression().trim();
        User user = userService.findByUsername(authentication.getName());
        Operation operation = operationService.getValidOperationData(mathematicalExpression);
        Double result;
        UserRecord userRecord;

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
                    userRecord = createUserRecord(SUCCESS_RECORD_CREATED_LOG_MSG, operation, updatedUser,
                            operation.getCost(), updatedUser.getBalance(), mathematicalExpression, result);

                } catch (Exception e) {
                    userRecord = createUserRecord(INVALID_MATHEMATICAL_EXPRESSION_LOG_MSG, null, user,
                            operation.getCost(), user.getBalance(), mathematicalExpression, null);
                }
            } else {
                userRecord = createUserRecord(MATHEMATICAL_EXPRESSION_NOT_SUPPORTED_LOG_MSG, null, user,
                        operation.getCost(), user.getBalance(), mathematicalExpression, null);
            }
        } else {
            userRecord = createUserRecord(INSUFFICIENT_BALANCE_LOG_MSG, null, user, operation.getCost(),
                    user.getBalance(), mathematicalExpression, null);

        }
        UserRecord savedUserRecord = userRecordService.save(userRecord);
        log.info("User record successfully created");
        return mapToUserRecordDto(savedUserRecord);
    }

    private UserRecord createUserRecord(String logMessage,
                                        Operation operation,
                                        User user,
                                        BigDecimal amount,
                                        BigDecimal userBalance,
                                        String operationValue,
                                        Double operationResponse) {
        if (operation == null) {
            operation = operationService.findByOperationType(OperationType.INVALID);
            if (logMessage.contains("is invalid") || logMessage.contains("is not supported")) {
                log.error(logMessage, operationValue);
            } else if (logMessage.contains("Insufficient balance")) {
                log.error(logMessage);
            }
        } else {
            log.info(logMessage, userBalance);
        }
        return new UserRecord(operation, user, amount, userBalance, operationValue, operationResponse);
    }
    
}
