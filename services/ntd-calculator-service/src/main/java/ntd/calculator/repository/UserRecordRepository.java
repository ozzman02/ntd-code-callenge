package ntd.calculator.repository;

import ntd.calculator.domain.User;
import ntd.calculator.domain.UserRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {

    Page<UserRecord> findByUser(User user, Pageable pageable);

}
