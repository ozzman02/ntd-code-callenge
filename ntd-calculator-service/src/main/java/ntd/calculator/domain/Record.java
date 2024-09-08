package ntd.calculator.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "records")
public class Record extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal amount;

    @Column(name = "user_balance")
    private BigDecimal userBalance;

    @Column(name = "operation_value")
    private String operationValue;

    @Column(name = "operation_response")
    private Double operationResponse;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Record record = (Record) o;
        return Objects.equals(operation, record.operation) && Objects.equals(user, record.user) && Objects.equals(amount, record.amount) && Objects.equals(userBalance, record.userBalance) && Objects.equals(operationValue, record.operationValue) && Objects.equals(operationResponse, record.operationResponse);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(operation);
        result = 31 * result + Objects.hashCode(user);
        result = 31 * result + Objects.hashCode(amount);
        result = 31 * result + Objects.hashCode(userBalance);
        result = 31 * result + Objects.hashCode(operationValue);
        result = 31 * result + Objects.hashCode(operationResponse);
        return result;
    }
}
