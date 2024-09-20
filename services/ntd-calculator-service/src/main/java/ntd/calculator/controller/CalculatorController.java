package ntd.calculator.controller;

import lombok.RequiredArgsConstructor;
import ntd.calculator.dto.MathematicalExpressionDto;
import ntd.calculator.service.CalculatorService;
import ntd.calculator.service.MathExpGeneratorService;
import ntd.calculator.service.UserRecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ntd.calculator.constants.ApplicationConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CALCULATOR_URL)
public class CalculatorController {

    private final CalculatorService calculatorService;

    private final MathExpGeneratorService mathExpGeneratorService;

    private final UserRecordService userRecordService;

    @PostMapping
    public ResponseEntity<?> calculateMathematicalExpression(
            @RequestBody MathematicalExpressionDto mathematicalExpressionDto) {
        return new ResponseEntity<>(calculatorService.calculate(mathematicalExpressionDto), HttpStatus.OK);
    }

    @GetMapping(GENERATOR_URL)
    public String generateRandomMathExpression() { return mathExpGeneratorService.generate(); }

    @GetMapping(USER_RECORDS_URL)
    public ResponseEntity<Page<?>> getUserRecords(@PathVariable Integer page) {
        return new ResponseEntity<>(userRecordService
                .findByUser(PageRequest.of(page, PAGE_SIZE)), HttpStatus.OK);
    }

}
