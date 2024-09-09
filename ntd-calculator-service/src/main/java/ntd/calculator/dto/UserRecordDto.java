package ntd.calculator.dto;

import lombok.*;
import ntd.calculator.domain.OperationType;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRecordDto {

    private Long id;

    private Long operationId;

    private OperationType operationType;

    private Long userId;

    private String username;

    private BigDecimal amount;

    private BigDecimal userBalance;

    private String operationValue;

    private Timestamp createdDate;

    private Double operationResponse;

}
