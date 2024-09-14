package ntd.calculator.service.impl;

import lombok.RequiredArgsConstructor;
import ntd.calculator.service.MathExpGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static ntd.calculator.constants.ApplicationConstants.GET_RANDOM_NUMBERS_REQUEST;
import static ntd.calculator.util.ApplicationUtil.getRandomArithmeticOperator;

@Service
@RequiredArgsConstructor
public class MathExpGeneratorServiceImpl implements MathExpGeneratorService {

    @Override
    public String generate() {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder mathematicalExpressionBuilder = new StringBuilder();
        ResponseEntity<String> randomNumbers = restTemplate.getForEntity(GET_RANDOM_NUMBERS_REQUEST, String.class);
        String replacedStr = Objects.requireNonNull(randomNumbers.getBody()).replace("\n", ",");
        for (char c : replacedStr.toCharArray()) {
            if (c == ',') {
                mathematicalExpressionBuilder.append(getRandomArithmeticOperator());
            } else {
                mathematicalExpressionBuilder.append(c);
            }
        }
        return mathematicalExpressionBuilder.substring(0, replacedStr.length() - 1);
    }

}
