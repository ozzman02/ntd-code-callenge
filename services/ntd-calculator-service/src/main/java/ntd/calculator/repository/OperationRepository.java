package ntd.calculator.repository;

import ntd.calculator.domain.Operation;
import ntd.calculator.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    Operation findByOperationType(OperationType operationType);

}
