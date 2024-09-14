package ntd.calculator.controller;

import lombok.RequiredArgsConstructor;
import ntd.calculator.dto.MathematicalExpressionDto;
import ntd.calculator.service.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ntd.calculator.constants.ApplicationConstants.CALCULATOR_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(CALCULATOR_URL)
public class CalculatorController {

    private final CalculatorService calculatorService;

    @PostMapping
    public ResponseEntity<?> calculateMathematicalExpression(
            @RequestBody MathematicalExpressionDto mathematicalExpressionDto) {
        return new ResponseEntity<>(calculatorService.calculate(mathematicalExpressionDto), HttpStatus.OK);
    }

}
