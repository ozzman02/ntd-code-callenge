package ntd.calculator.service;

import ntd.calculator.dto.MathematicalExpressionDto;
import ntd.calculator.dto.UserRecordDto;

public interface CalculatorService {

    UserRecordDto calculate(MathematicalExpressionDto mathematicalExpressionDto);

}
