package ntd.calculator.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ntd.calculator.enums.UserStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {

    private String username;

    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private BigDecimal balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) &&
                status == user.status && Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(username);
        result = 31 * result + Objects.hashCode(password);
        result = 31 * result + Objects.hashCode(status);
        result = 31 * result + Objects.hashCode(balance);
        return result;
    }
}
