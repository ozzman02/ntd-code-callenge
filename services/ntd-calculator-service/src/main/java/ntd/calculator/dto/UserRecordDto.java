package ntd.calculator.dto;

import lombok.*;
import ntd.calculator.domain.OperationType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRecordDto that = (UserRecordDto) o;
        return Objects.equals(id, that.id) && Objects.equals(operationId, that.operationId)
                && operationType == that.operationType && Objects.equals(userId, that.userId)
                && Objects.equals(username, that.username) && Objects.equals(amount, that.amount)
                && Objects.equals(userBalance, that.userBalance)
                && Objects.equals(operationValue, that.operationValue)
                && Objects.equals(operationResponse, that.operationResponse);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(operationId);
        result = 31 * result + Objects.hashCode(operationType);
        result = 31 * result + Objects.hashCode(userId);
        result = 31 * result + Objects.hashCode(username);
        result = 31 * result + Objects.hashCode(amount);
        result = 31 * result + Objects.hashCode(userBalance);
        result = 31 * result + Objects.hashCode(operationValue);
        result = 31 * result + Objects.hashCode(operationResponse);
        return result;
    }

    @Override
    public String toString() {
        return "UserRecordDto{" +
                "id=" + id +
                ", operationId=" + operationId +
                ", operationType=" + operationType +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                ", userBalance=" + userBalance +
                ", operationValue='" + operationValue + '\'' +
                ", createdDate=" + createdDate +
                ", operationResponse=" + operationResponse +
                '}';
    }
}
