package ntd.calculator.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "records")
public class UserRecord extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -6559777780056487793L;

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

        UserRecord userRecord = (UserRecord) o;
        return Objects.equals(operation, userRecord.operation) && Objects.equals(user, userRecord.user) && Objects.equals(amount, userRecord.amount) && Objects.equals(userBalance, userRecord.userBalance) && Objects.equals(operationValue, userRecord.operationValue) && Objects.equals(operationResponse, userRecord.operationResponse);
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
