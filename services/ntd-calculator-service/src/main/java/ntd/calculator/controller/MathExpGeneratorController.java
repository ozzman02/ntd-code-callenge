package ntd.calculator.controller;

import lombok.RequiredArgsConstructor;
import ntd.calculator.service.MathExpGeneratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ntd.calculator.constants.ApplicationConstants.GENERATOR_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(GENERATOR_URL)
public class MathExpGeneratorController {

    private final MathExpGeneratorService mathExpGeneratorService;

    @GetMapping
    public String generateRandomMathExpression() { return mathExpGeneratorService.generate(); }

}
