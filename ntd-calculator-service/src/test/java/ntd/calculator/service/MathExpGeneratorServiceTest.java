package ntd.calculator.service;

import ntd.calculator.service.impl.MathExpGeneratorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static ntd.calculator.util.ApplicationUtil.isValidMathematicalExpression;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class MathExpGeneratorServiceTest {

    @InjectMocks
    private MathExpGeneratorServiceImpl mathExpGeneratorService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void generateTest() {
        assertThat(isValidMathematicalExpression(mathExpGeneratorService.generate()))
                .isEqualTo(true);
    }

}
