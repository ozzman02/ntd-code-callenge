package ntd.calculator.service;

import ntd.calculator.domain.Operation;
import ntd.calculator.domain.OperationType;

public interface OperationService {

    Operation findByOperationType(OperationType operationType);

    Operation getValidOperationData(String mathematicalExpression, boolean special);

}
