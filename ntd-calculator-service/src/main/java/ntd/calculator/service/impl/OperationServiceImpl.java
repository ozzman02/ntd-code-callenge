package ntd.calculator.service.impl;

import lombok.RequiredArgsConstructor;
import ntd.calculator.domain.Operation;
import ntd.calculator.domain.OperationType;
import ntd.calculator.repository.OperationRepository;
import ntd.calculator.service.OperationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;

    @Override
    public Operation findByOperationType(OperationType operationType) {
        return operationRepository.findByOperationType(operationType);
    }

    @Override
    public Operation getValidOperationData(String mathematicalExpression, boolean special) {
        OperationType operationType = null;
        int mathematicalExpressionLength = mathematicalExpression.trim().length();
        if (special) {
            operationType = OperationType.SPECIAL;
        } else if (mathematicalExpressionLength > 1 && mathematicalExpressionLength < 20) {
            operationType = OperationType.STANDARD;
        } else if (mathematicalExpressionLength > 20 && mathematicalExpressionLength <= 3000) {
            operationType = OperationType.COMPLEX;
        }
        return operationRepository.findByOperationType(operationType);
    }

}
