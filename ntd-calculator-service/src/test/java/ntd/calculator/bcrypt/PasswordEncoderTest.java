package ntd.calculator.bcrypt;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {

    @Test
    void bcryptPasswordEncoderGen() {
        PasswordEncoder bcrypt = new BCryptPasswordEncoder(15);
        System.out.println(bcrypt.encode("user1pwd"));
        System.out.println(bcrypt.encode("user2pwd"));
        System.out.println(bcrypt.encode("user3pwd"));
    }
}
